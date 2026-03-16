package com.cloudessentials.parking_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type", nullable = false)
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSlot slot;

    @Column(name = "entry_time", nullable = false)
    private LocalDateTime entryTime;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @Column(name = "total_hours")
    private Double totalHours;

    @Column(name = "total_charges")
    private Double totalCharges;

    @Column(name = "payment_status")
    private String paymentStatus = "PENDING";

    @Column(name = "record_status")
    private String recordStatus = "PARKED";
}