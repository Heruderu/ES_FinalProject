/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightserver.services;

import com.flightserver.models.Airlines;
import com.flightserver.models.Airports;
import com.flightserver.models.Flight;
import com.flightserver.scheduler.FlightScheduler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author HParaense
 */
@Service
public class FlightService {
    
    private static final Logger log = LoggerFactory.getLogger(FlightScheduler.class);
    private final AtomicLong counter = new AtomicLong();
    private final Random RANDOM = new Random();
    private final Airlines[] airlines = Airlines.values();
    private final Airports[] airports = Airports.values();
    private List<Flight> allFlights = new ArrayList<>();

    public AtomicLong getCounter() {
        return counter;
    }

    public Random getRANDOM() {
        return RANDOM;
    }

    public Airlines[] getAirlines() {
        return airlines;
    }

    public Airports[] getAirports() {
        return airports;
    }

    public List<Flight> getAllFlights() {
        log.info("Returning all flights.");
        return allFlights;
    }

    public void addFlight(Flight flight) {
        this.allFlights.add(flight);
    }
    
    public void clearFlights() {
        if (!allFlights.isEmpty()) {
            log.info("Clearing all flights.");
            this.allFlights.clear();
        }
    }
    
    public Object createFlights(String num) {
        if (!num.isEmpty()) return createMultipleFlights(num);
        else return createSingleFlight();
    }
    
    public Flight createSingleFlight() {
        Date departureDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(departureDate);
        calendar.add(Calendar.HOUR_OF_DAY, RANDOM.nextInt(8));
        Date arrivalDate = calendar.getTime();
        String departureAirport = airports[RANDOM.nextInt(airports.length)].name();
        String arrivalAirport = "";
        while (arrivalAirport.isEmpty()) {
            arrivalAirport = airports[RANDOM.nextInt(airports.length)].name();
            if (arrivalAirport.equals(departureAirport)) arrivalAirport = "";
        }
        long id = counter.incrementAndGet();
        Flight flight = new Flight(id, String.format("%s%04d", airlines[RANDOM.nextInt(airlines.length)], id), 
                departureDate, arrivalDate, departureAirport, arrivalAirport);
        allFlights.add(flight);
        log.info("Creating a flight.");
        return flight;
    }
    
    public List<Flight> createMultipleFlights(String num) {
        Integer number = Integer.parseInt(num);
        List<Flight> flightsRequested = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Date departureDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(departureDate);
            calendar.add(Calendar.HOUR_OF_DAY, RANDOM.nextInt(8));
            Date arrivalDate = calendar.getTime();
            String departureAirport = airports[RANDOM.nextInt(airports.length)].name();
            String arrivalAirport = "";
            while (arrivalAirport.isEmpty()) {
                arrivalAirport = airports[RANDOM.nextInt(airports.length)].name();
                if (arrivalAirport.equals(departureAirport)) arrivalAirport = "";
            }
            long id = counter.incrementAndGet();
            Flight flight = new Flight(id, String.format("%s%04d", airlines[RANDOM.nextInt(airlines.length)], id), 
                    departureDate, arrivalDate, departureAirport, arrivalAirport);
            allFlights.add(flight);
            flightsRequested.add(flight);
        }
        log.info("Creating " + num + " flights.");
        return flightsRequested;
    }
        
    public List<Flight> getRequestedFlights(String num) {
        Integer number = Integer.parseInt(num);
        List<Flight> flightsRequested = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            flightsRequested.add(allFlights.get(i));
        }
        log.info("Returning " + num + " flights.");
        return flightsRequested;
    }
}
