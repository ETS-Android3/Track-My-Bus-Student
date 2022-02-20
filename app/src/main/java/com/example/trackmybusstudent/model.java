package com.example.trackmybusstudent;

import android.widget.TextView;

public class model {
   String Drivers_Name;
           int Route_Number;
           double Longitude;
           double Latitude;
           String id ;
           String Bus_City;


    public model() {
    }

    public model(String drivers_Name, int route_Number, double l_longitude, double l_latitude, String bus_City) {
        Drivers_Name = drivers_Name;
        Route_Number = route_Number;
        Longitude = l_longitude;
        Bus_City = bus_City;
    }

    public String getDrivers_Name() {
        return Drivers_Name;
    }

    public void setDrivers_Name(String drivers_Name) {
        Drivers_Name = drivers_Name;
    }

    public int getRoute_Number() {
        return Route_Number;
    }

    public void setRoute_Number(int route_Number) {
        Route_Number = route_Number;
    }

    public double getL_Longitude() {
        return Longitude;
    }

    public String getBus_City() {
        return Bus_City;
    }

    public void setBus_City(String bus_City) {
        Bus_City = bus_City;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setL_Longitude(double l_Longitude) {
        Longitude = l_Longitude;
    }

    public double getL_Latitude() {
        return Latitude;
    }

    public void setL_Latitude(double l_Latitude) {
        Latitude = l_Latitude;
    }

}
