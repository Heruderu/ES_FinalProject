/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flightapp.model.Flight;
import com.flightapp.repository.FlightRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author HParaense
 */
@Service
public class FlightService {
    private List<Flight> flights;
    private String error = "Error 404!";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private FlightRepository repository;
    
    public FlightService() {
    }
    
    public String getFlight() throws JsonProcessingException {
        setFlight();
        if (this.error != null) {
            return this.error;
        }
        Gson gson = new Gson();
        String json = gson.toJson(this.flights);
        if (json.equals("[]")) json = "No flights found!";
        return json;
    }
    
    @Transactional
    public void setFlight() {
        this.error = null;

        String uri = System.getenv("SERVER_HOST");
        uri += "flights";
        
        try {
            Flight requestedFlights[] = restTemplate.getForObject(uri, Flight[].class);
            List<Flight> portugalFlights = new ArrayList<>();
            for (Flight f : requestedFlights) {
                if ((f.getDepartureAirport() != null && f.getDepartureAirport().equals("LIS")) || 
                        (f.getArrivalAirport() != null && f.getArrivalAirport().equals("LIS")) || 
                        (f.getDepartureAirport() != null && f.getDepartureAirport().equals("OPO")) || 
                        (f.getArrivalAirport() != null && f.getArrivalAirport().equals("OPO"))) {
                    portugalFlights.add(f);
                    repository.save(f);
                }
            }
            this.flights = portugalFlights;
        } catch (HttpClientErrorException.NotFound e) {
            this.error = "No flights found!";
        }
    }
    
    @Transactional
    public List<Flight> showAll() {
        List<Flight> results = (List<Flight>) repository.findAll();
        return results;
    }
    
    @Transactional
    public List<Flight> showByAirlineCompany(String company) {
        if (company.isEmpty()) return showAll();
        Iterable<Flight> results = repository.findAll();
        List<Flight> requestedFlights = new ArrayList<>();
        for (Flight result : results) {
            if (result.getAirline().equals(company))
                requestedFlights.add(result);
        }
        return requestedFlights;
    }

    public void delete(String id) {
        repository.deleteById(Integer.parseInt(id));
        Iterable<Flight> results = repository.findAll();
        List<Flight> requestedFlights = new ArrayList<>();
        for (Flight result : results) {
            requestedFlights.add(result);
        }
        this.flights = requestedFlights;
    }
    
}
