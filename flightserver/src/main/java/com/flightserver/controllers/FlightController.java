/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightserver.controllers;

import com.flightserver.models.Airlines;
import com.flightserver.models.Airports;
import com.flightserver.models.Flight;
import com.flightserver.services.FlightService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HParaense
 */

@RestController
public class FlightController {
    
    @Autowired FlightService flightService;
    
    @GetMapping("/flight")
    public Object flight(@RequestParam(value = "num", defaultValue = "") String num) {
        if (num.isEmpty()) return flightService.createSingleFlight();
        else return flightService.createMultipleFlights(num);
    }
    
    @GetMapping("/flights")
    public List<Flight> flights(@RequestParam(value = "num", defaultValue = "all") String num) {
        if (num.equals("all")) return flightService.getAllFlights();
        else return flightService.getRequestedFlights(num);
    }

}
