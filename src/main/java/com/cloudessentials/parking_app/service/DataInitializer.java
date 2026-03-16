package com.cloudessentials.parking_app.service;

import com.cloudessentials.parking_app.model.ParkingSlot;
import com.cloudessentials.parking_app.model.VehicleType;
import com.cloudessentials.parking_app.repository.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ParkingSlotRepository slotRepository;

    @Override
    public void run(String... args) {
        if (slotRepository.count() == 0) {
            // Floor 1: 10 Two-Wheeler slots
            for (int i = 1; i <= 10; i++) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotNumber("TW-" + String.format("%02d", i));
                slot.setVehicleType(VehicleType.TWO_WHEELER);
                slot.setOccupied(false);
                slot.setFloorNumber(1);
                slotRepository.save(slot);
            }
            // Floor 2: 10 Four-Wheeler slots
            for (int i = 1; i <= 10; i++) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotNumber("FW-" + String.format("%02d", i));
                slot.setVehicleType(VehicleType.FOUR_WHEELER);
                slot.setOccupied(false);
                slot.setFloorNumber(2);
                slotRepository.save(slot);
            }
            // Floor 3: 5 Heavy Vehicle slots
            for (int i = 1; i <= 5; i++) {
                ParkingSlot slot = new ParkingSlot();
                slot.setSlotNumber("HV-" + String.format("%02d", i));
                slot.setVehicleType(VehicleType.HEAVY);
                slot.setOccupied(false);
                slot.setFloorNumber(3);
                slotRepository.save(slot);
            }
            System.out.println("✅ 25 Parking slots created!");
        }
    }
}