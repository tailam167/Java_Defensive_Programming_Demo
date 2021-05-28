package com.defcoding.entities;

import java.time.LocalDate;
import java.util.Optional;

public class Flight {

    private String fromDest;
    private String toDest;
    private LocalDate date;
    private int seatsAvailable;
    private PlaneModel planeModel;

    public Flight(String fromDest,
                  String toDest,
                  LocalDate date,
                  int seatsAvailable, 
                  PlaneModel planeModel){
        this.fromDest = fromDest;
        this.toDest = toDest;
        this.date = date;
        this.seatsAvailable = seatsAvailable;
        // version 1 
        this.planeModel = Optional.ofNullable(planeModel).orElse(PlaneModel.UNKNOWN);
        //version 2
        this.planeModel = planeModel != null ? planeModel : PlaneModel.UNKNOWN;
    }

    public Flight(String fromDest, String toDest, LocalDate date, int seatsAvailable) {
		super();
		this.fromDest = fromDest;
		this.toDest = toDest;
		this.date = date;
		this.seatsAvailable = seatsAvailable;
	}

	public String getFromDest() {
        return fromDest;
    }

    public String getToDest() {
        return toDest;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public PlaneModel getPlaneModel() {
		return planeModel;
	}

	@Override
    public String toString(){
        return String.format("From %s to %s. Date: %s. Available seats left: %s", fromDest, toDest, date, seatsAvailable);
    }
}
