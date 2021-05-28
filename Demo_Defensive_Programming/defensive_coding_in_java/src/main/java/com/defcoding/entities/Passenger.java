package com.defcoding.entities;

public class Passenger {

    private String name;
    private String surnmae;
    
    /**
     * Constructor of Passenger Class
     *
     * @param name
     * @param surnmae
     * @author taila
     */
	public Passenger(String name, String surnmae) {
		super();
		this.name = name;
		this.surnmae = surnmae;
	}

	public String getName() {
		return name;
	}
	
	public String getSurnmae() {
		return surnmae;
	}
}
