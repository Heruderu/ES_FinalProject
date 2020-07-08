/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightserver.models;

import java.util.Date;

/**
 *
 * @author HParaense
 */
public class Flight {
    
    private final long id;
    private final String airline;
    private final Date departureDate;
    private final Date arrivalDate;
    private final String departureAirport;
    private final String arrivalAirport;
    private final long duration;

    public Flight(long id, String airline, Date departureDate, Date arrivalDate, String departureAirport, String arrivalAirport) {
        this.id = id;
        this.airline = airline;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.duration = Math.abs(arrivalDate.getTime() - departureDate.getTime());
    }

    public long getId() {
        return id;
    }

    public String getAirline() {
        return airline;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public long getDuration() {
        return duration;
    }
    
}
