package com.defcoding.entities;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Booking {

    private String referenceId;
    private List<Passenger> passengerList;
    private Flight flight;

    public Booking(String referenceId, List<Passenger> passengers, Flight flight){

        this.referenceId = isInvalidString(referenceId);
        this.passengerList = validateList(passengers);
        this.flight = requireNonNull(flight, "A booking cannot be created with a Flight null reference");
    }

    private List<Passenger> validateList(List<Passenger> passengers) {
        if( passengers == null || passengers.isEmpty()){
            throw new IllegalArgumentException("A list of passengers " +
                    "cannot be null and must contain at least one passenger");
        }
        return passengers;
    }

    private String isInvalidString(String s){
        if(s == null || s.trim().isEmpty()){
            throw new IllegalArgumentException("You have provided the following argument, but it cannot be null or empty:" + s);
        }
        return s;

    }

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
  
}
