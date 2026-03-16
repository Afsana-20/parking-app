package com.cloudessentials.parking_app.model;

public enum VehicleType {
    TWO_WHEELER("2-Wheeler", 10.0, "🏍️"),
    FOUR_WHEELER("4-Wheeler", 20.0, "🚗"),
    HEAVY("Heavy Vehicle", 40.0, "🚛");

    private final String label;
    private final double ratePerHour;
    private final String icon;

    VehicleType(String label, double ratePerHour, String icon) {
        this.label = label;
        this.ratePerHour = ratePerHour;
        this.icon = icon;
    }

    public String getLabel() { return label; }
    public double getRatePerHour() { return ratePerHour; }
    public String getIcon() { return icon; }
}

