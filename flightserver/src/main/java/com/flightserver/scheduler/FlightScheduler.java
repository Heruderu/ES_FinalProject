/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightserver.scheduler;

import com.flightserver.controllers.FlightController;
import com.flightserver.models.Flight;
import com.flightserver.services.FlightService;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author HParaense
 */
@Component
public class FlightScheduler {
    
    private static final Logger LOG = LoggerFactory.getLogger(FlightScheduler.class);
    private static final String NUMFLIGHTS = "5";
    
    @Autowired FlightService flightService;
        
    @Scheduled(fixedRate = 300000)
    public void deleteFlights() {
        flightService.clearFlights();
    }
    
    @Scheduled(fixedRate = 60000)
    public void createFlights() {
        flightService.createMultipleFlights(NUMFLIGHTS);
    }
    
}
