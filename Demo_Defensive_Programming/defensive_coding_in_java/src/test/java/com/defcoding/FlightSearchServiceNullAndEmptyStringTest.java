package com.defcoding;

import com.defcoding.entities.SearchRequest;
import org.junit.jupiter.api.Test;

import static com.defcoding.FlightSearchService.flightSearch;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlightSearchServiceNullAndEmptyStringTest {

    private static final FlightSearchService searchService = flightSearch();
    private static final String validFromDest = "Boston";
    private static final String validToDest = "Washington";
    private static final String date = "15/10/2019";
    private static final String validPassengerNum = "2";

    @Test
    void invalidDestinationFirstArgNotAllowed(){
    	
    	assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(validPassengerNum, date, null, validToDest)));

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest("", validToDest, date, validPassengerNum)));

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(" ", validToDest, date, validPassengerNum)));

    }

    @Test
    void invalidDestinationSecondArgNotAllowed(){
        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(null, date, validPassengerNum)));

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(validFromDest, "", date, validPassengerNum)));

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(" ", date, validPassengerNum)));
    }

    @Test
    void invalidDateNotAllowed(){
        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(validFromDest, validToDest, null, validPassengerNum)));

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(validFromDest, validToDest, "", validPassengerNum)));

        assertThrows(IllegalArgumentException.class,
                () -> searchService.search(new SearchRequest(validFromDest, validToDest, "  ", validPassengerNum)));
    }


}
