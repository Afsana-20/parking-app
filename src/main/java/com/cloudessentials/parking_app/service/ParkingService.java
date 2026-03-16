package com.cloudessentials.parking_app.service;

import com.cloudessentials.parking_app.model.ParkingRecord;
import com.cloudessentials.parking_app.model.ParkingSlot;
import com.cloudessentials.parking_app.model.VehicleType;
import com.cloudessentials.parking_app.repository.ParkingRecordRepository;
import com.cloudessentials.parking_app.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    @Autowired
    private ParkingSlotRepository slotRepository;

    @Autowired
    private ParkingRecordRepository recordRepository;

    public List<ParkingSlot> getAllSlots() {
        return slotRepository.findAll();
    }

    public long countAvailable(VehicleType type) {
        return slotRepository.countByVehicleTypeAndOccupied(type, false);
    }

    public long countOccupied(VehicleType type) {
        return slotRepository.countByVehicleTypeAndOccupied(type, true);
    }

    @Transactional
    public ParkingRecord vehicleEntry(String vehicleNumber, String ownerName,
                                      String mobileNumber, VehicleType vehicleType) {
        Optional<ParkingRecord> existing = recordRepository
                .findByVehicleNumberAndRecordStatus(vehicleNumber.toUpperCase(), "PARKED");
        if (existing.isPresent()) {
            throw new RuntimeException("Vehicle " + vehicleNumber +
                    " is already parked in slot " + existing.get().getSlot().getSlotNumber());
        }

        ParkingSlot slot = slotRepository
                .findFirstByVehicleTypeAndOccupied(vehicleType, false)
                .orElseThrow(() -> new RuntimeException(
                        "No available slots for " + vehicleType.getLabel() + ". Parking is full!"));

        slot.setOccupied(true);
        slotRepository.save(slot);

        ParkingRecord record = new ParkingRecord();
        record.setVehicleNumber(vehicleNumber.toUpperCase());
        record.setOwnerName(ownerName);
        record.setMobileNumber(mobileNumber);
        record.setVehicleType(vehicleType);
        record.setSlot(slot);
        record.setEntryTime(LocalDateTime.now());
        record.setRecordStatus("PARKED");
        record.setPaymentStatus("PENDING");

        return recordRepository.save(record);
    }

    @Transactional
    public ParkingRecord vehicleExit(String vehicleNumber) {
        ParkingRecord record = recordRepository
                .findByVehicleNumberAndRecordStatus(vehicleNumber.toUpperCase(), "PARKED")
                .orElseThrow(() -> new RuntimeException(
                        "Vehicle " + vehicleNumber + " not found in parked records."));

        LocalDateTime exitTime = LocalDateTime.now();
        record.setExitTime(exitTime);

        long minutes = ChronoUnit.MINUTES.between(record.getEntryTime(), exitTime);
        double hours = Math.max(1.0, Math.ceil(minutes / 60.0));
        record.setTotalHours(hours);

        double charges = hours * record.getVehicleType().getRatePerHour();
        record.setTotalCharges(charges);
        record.setRecordStatus("EXITED");
        record.setPaymentStatus("PAID");

        ParkingSlot slot = record.getSlot();
        slot.setOccupied(false);
        slotRepository.save(slot);

        return recordRepository.save(record);
    }

    public List<ParkingRecord> getCurrentlyParked() {
        return recordRepository.findByRecordStatusOrderByEntryTimeDesc("PARKED");
    }

    public List<ParkingRecord> getAllRecords() {
        return recordRepository.findAllByOrderByEntryTimeDesc();
    }

    public long totalParked() {
        return recordRepository.countByRecordStatus("PARKED");
    }

    public long totalSlots() { return slotRepository.count(); }
    public long availableSlots() { return slotRepository.findByOccupied(false).size(); }
    public long occupiedSlots() { return slotRepository.findByOccupied(true).size(); }
}