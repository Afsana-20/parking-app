package com.cloudessentials.parking_app.repository;

import com.cloudessentials.parking_app.model.ParkingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRecordRepository extends JpaRepository<ParkingRecord, Long> {
    List<ParkingRecord> findByRecordStatusOrderByEntryTimeDesc(String status);
    List<ParkingRecord> findAllByOrderByEntryTimeDesc();
    Optional<ParkingRecord> findByVehicleNumberAndRecordStatus(String vehicleNumber, String status);
    long countByRecordStatus(String status);
}