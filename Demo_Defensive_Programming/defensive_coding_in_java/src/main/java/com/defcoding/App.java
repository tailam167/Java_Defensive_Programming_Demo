package com.defcoding;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Comparator.comparing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.defcoding.entities.Booking;
import com.defcoding.entities.Flight;
import com.defcoding.entities.Passenger;
import com.defcoding.entities.PlaneModel;
import com.defcoding.entities.SearchRequest;

import logging.ConsoleLogger;

import static com.defcoding.DateUtils.parseInputDate;
import static com.defcoding.FlightSearchService.flightSearch;

/**
 * Method Input
 * 
 * @author taila
 *
 */
public class App {

	/**
	 * Provide arguments, for example: 2 "15-10-2019" "Boston" "New York"
	 */
	public static void main(String[] args) {

		// Create logging
		ConsoleLogger consoleLogger = new ConsoleLogger();

		// Create new request from client
		String[] args1 = { "2", "15-10-2019", "Boston", "New York" };

		// Validating method input for handling defensive programming
		SearchRequest request = new SearchRequest(args1);

		FlightSearchService searchService = flightSearch();

		List<Flight> outboundFlights = searchService.search(request);

		displayFlights(outboundFlights);

		// Using Framework for handling defensive programming
		Passenger passengerJohn = new Passenger("John", "Thompson");
		Passenger passengerTai = new Passenger("Tai", "Lam");
		Passenger passengerNghi = new Passenger("Nghi", "To");
		ArrayList<Passenger> passengerList = new ArrayList<Passenger>();
		passengerList.add(passengerJohn);
		passengerList.add(passengerTai);
		passengerList.add(passengerNghi);

		Booking booking1 = new Booking("1607", passengerList, null);
		consoleLogger.writeNotify("You can't book the flight. Because " + booking1 + " is invalid");

		Booking booking2 = new Booking("1111", passengerList, outboundFlights.get(0));
		consoleLogger.writeInfor("Your booking: " + booking2.toString());

		// Improving Method Return Values

		List<Flight> flightList = getFlights();

		// Using Java Optional to find the earliest flight
		Optional<Flight> earliestFlight = flightList.stream().filter(f -> "Boston".equals(f.getFromDest()))
				.filter(f -> "San Francisco".equals(f.getToDest())).min(comparing(Flight::getDate));

		// Will fail with a NoSuchElementException if Optional is empty and you use
		// .get()
		// System.out.println("Earliest flight date: " +
		// earliestFlight.get().getDate());
		// so instead use one of these options
		// (1)
		consoleLogger.infoEarliestFlight("Earliest Flight: ",
				earliestFlight.orElse(new Flight("Boston", "San Jose", LocalDate.now().plus(1, DAYS), 2)));

		// (2)
		earliestFlight.ifPresentOrElse(x -> consoleLogger.inforOptinalFlight("Flight found: ", earliestFlight),
				() -> consoleLogger.writeNotify("Didn't find any flights for your search"));

		// (3)
		Flight foundFlight = earliestFlight.orElseThrow(NoFlightAvailable::new);
		consoleLogger.writeNotify("Flight found: " + foundFlight);
		
		// Using Other Defensive Practices
		// Java 7 case
		
		Path path = Paths.get("src/main/resource/demo.txt");
		write(path, "Text");

		try (FileOutputStream outStream = new FileOutputStream(path.toString());
				ObjectOutputStream stream = new ObjectOutputStream(outStream)) {
			// write
		} catch (IOException e) {
			// handle e properly
		}
		
		// Pre-Java 7 case
		FileOutputStream outStream = null;
	    try {
	        outStream = new FileOutputStream(path.toString());
	        ObjectOutputStream stream = new ObjectOutputStream(outStream);
	            // write
	            stream.close();
	        } catch (IOException e) {
	            // handle e properly
	        } finally {
	            if (outStream != null) {
	                try {
	                    outStream.close();
	                } catch (Exception e) {
	                    // handle e properly
	                }
	            }
	        }
	}

	private static List<Flight> getFlights() {
		return List.of(
				// date format DD-MM-YYYY
				new Flight("Boston", "New York", parseInputDate("17-10-2019"), 1, PlaneModel.AIRBUS_320),
				new Flight("Boston", "New York", parseInputDate("15-10-2019"), 10, PlaneModel.BOEING737_700),
				new Flight("Boston", "New York", parseInputDate("16-10-2019"), 6, null),
				new Flight("Boston", "New York", parseInputDate("15-10-2019"), 7, PlaneModel.BOEING737_800),
				new Flight("New York", "Boston", parseInputDate("18-10-2019"), 2, PlaneModel.AIRBUS_320),
				new Flight("New York", "Boston", parseInputDate("19-10-2019"), 2, null));
	}

	@SuppressWarnings("serial")
	private static class NoFlightAvailable extends RuntimeException {
	}

	private static void write(Path path, String text) {
		try {
			Files.write(path, text.getBytes());
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}

	private static void displayFlights(List<Flight> outboundFlights) {

		// Create logging
		ConsoleLogger consoleLogger = new ConsoleLogger();

		if (outboundFlights.size() == 0) {
			consoleLogger.writeNotify("No flights found for your search criteria.");
		} else {
			consoleLogger.writeNotify("Flights found:");
			consoleLogger.writeInfor(Arrays.toString(outboundFlights.toArray()));
		}
	}
}
