package com.defcoding;

import java.util.List;

import com.defcoding.entities.Flight;

public interface FlightStore {

    public List<Flight> getFlights();
    
    public Flight getOneFlight();
}
