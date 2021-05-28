package com.defcoding.entities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import logging.ConsoleLogger;

import static com.defcoding.DateUtils.parseInputDate;
import static java.util.stream.Collectors.toList;

/**
 * Improving Method Return Values
 * 
 * @author taila
 *
 */
public class RemainingFlights {

    static LocalDateTime now = LocalDateTime.now();
    static LocalDateTime endOfDay = now.with(LocalTime.MAX);

    static List<Flight> remainingFlights = getFlights(now, endOfDay);

    private static List<Flight> getRemainingFlights(){
        return new ArrayList<>(remainingFlights);
    }

    public static void main(String[] args){
    	
    	// Create logging
    	ConsoleLogger consoleLogger = new ConsoleLogger();

        List<Flight> flights = getRemainingFlights();

        if(flights.isEmpty()){
           consoleLogger.writeNotify("No flights today anymore");
        }
    }

    @SuppressWarnings("unlikely-arg-type")
	static List<Flight> getFlights(LocalDateTime start, LocalDateTime end){
        return getFlights().stream()
        		.filter(f -> f.getDate().equals(start))
        		.filter(f -> f.getDate().equals(end))
                .collect(toList());
    }

    private static List<Flight> getFlights(){
        return List.of(
                // date format DD-MM-YYYY
                new Flight("Boston","New York",parseInputDate("17-10-2019"), 1, PlaneModel.AIRBUS_320),
                new Flight("Boston","New York",parseInputDate("15-10-2019"), 10, PlaneModel.BOEING737_700),
                new Flight("Boston","New York",parseInputDate("16-10-2019"), 6, null),
                new Flight("Boston","New York",parseInputDate("15-10-2019"), 7, PlaneModel.BOEING737_800),


                new Flight("New York","Boston",parseInputDate("18-10-2019"), 2, PlaneModel.AIRBUS_320),
                new Flight("New York","Boston",parseInputDate("19-10-2019"), 2, null)
        );
    }
}
