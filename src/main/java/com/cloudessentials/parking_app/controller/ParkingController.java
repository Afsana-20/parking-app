package com.cloudessentials.parking_app.controller;

import com.cloudessentials.parking_app.model.ParkingRecord;
import com.cloudessentials.parking_app.model.VehicleType;
import com.cloudessentials.parking_app.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalSlots", parkingService.totalSlots());
        model.addAttribute("availableSlots", parkingService.availableSlots());
        model.addAttribute("occupiedSlots", parkingService.occupiedSlots());
        model.addAttribute("totalParked", parkingService.totalParked());
        model.addAttribute("twAvail", parkingService.countAvailable(VehicleType.TWO_WHEELER));
        model.addAttribute("twOccupied", parkingService.countOccupied(VehicleType.TWO_WHEELER));
        model.addAttribute("fwAvail", parkingService.countAvailable(VehicleType.FOUR_WHEELER));
        model.addAttribute("fwOccupied", parkingService.countOccupied(VehicleType.FOUR_WHEELER));
        model.addAttribute("hvAvail", parkingService.countAvailable(VehicleType.HEAVY));
        model.addAttribute("hvOccupied", parkingService.countOccupied(VehicleType.HEAVY));
        model.addAttribute("parkedVehicles", parkingService.getCurrentlyParked());
        return "dashboard";
    }

    @GetMapping("/entry")
    public String entryPage(Model model) {
        model.addAttribute("vehicleTypes", VehicleType.values());
        return "entry";
    }

    @PostMapping("/entry")
    public String processEntry(@RequestParam String vehicleNumber,
                               @RequestParam String ownerName,
                               @RequestParam String mobileNumber,
                               @RequestParam VehicleType vehicleType,
                               RedirectAttributes redirectAttributes) {
        try {
            ParkingRecord record = parkingService.vehicleEntry(
                    vehicleNumber, ownerName, mobileNumber, vehicleType);
            redirectAttributes.addFlashAttribute("success",
                    "✅ Vehicle " + record.getVehicleNumber() +
                    " parked in slot " + record.getSlot().getSlotNumber());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "❌ " + e.getMessage());
        }
        return "redirect:/entry";
    }

    @GetMapping("/exit")
    public String exitPage() { return "exit"; }

    @PostMapping("/exit")
    public String processExit(@RequestParam String vehicleNumber,
                              RedirectAttributes redirectAttributes) {
        try {
            ParkingRecord record = parkingService.vehicleExit(vehicleNumber);
            redirectAttributes.addFlashAttribute("bill", record);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "❌ " + e.getMessage());
        }
        return "redirect:/exit";
    }

    @GetMapping("/records")
    public String allRecords(Model model) {
        model.addAttribute("records", parkingService.getAllRecords());
        return "records";
    }

    @GetMapping("/slots")
    public String slotsPage(Model model) {
        model.addAttribute("slots", parkingService.getAllSlots());
        return "slots";
    }
}