package com.example.android.tether;

public class User {
    private String id;
    private double latitude;
    private double longitude;
    private double elevation;

    public User (String id, double latitude, double longitude, double elevation) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    public String getId() { return this.id; }
    public double getLatitude() { return this.latitude; }
    public double getLongitude() { return this.longitude; }
    public double getElevation() { return this.elevation; }


}
