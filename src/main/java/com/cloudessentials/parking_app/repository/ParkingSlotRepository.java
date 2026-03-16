package com.cloudessentials.parking_app.repository;

import com.cloudessentials.parking_app.model.ParkingSlot;
import com.cloudessentials.parking_app.model.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByVehicleType(VehicleType vehicleType);
    List<ParkingSlot> findByOccupied(boolean occupied);
    List<ParkingSlot> findByVehicleTypeAndOccupied(VehicleType vehicleType, boolean occupied);
    Optional<ParkingSlot> findFirstByVehicleTypeAndOccupied(VehicleType vehicleType, boolean occupied);
    long countByVehicleTypeAndOccupied(VehicleType vehicleType, boolean occupied);
}