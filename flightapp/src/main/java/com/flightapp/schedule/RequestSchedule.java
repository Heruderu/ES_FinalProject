/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightapp.schedule;

import com.flightapp.service.FlightService;
import java.text.SimpleDateFormat;
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
public class RequestSchedule {
    @Autowired FlightService flightService;
    
    private static final Logger LOG = LoggerFactory.getLogger(RequestSchedule.class);

    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        LOG.info("The time is now {}", DATEFORMAT.format(new Date()));
        flightService.setFlight();
        
    }
}
