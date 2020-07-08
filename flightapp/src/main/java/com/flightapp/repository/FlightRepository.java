/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightapp.repository;

import com.flightapp.model.Flight;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author HParaense
 */
public interface FlightRepository extends CrudRepository<Flight, Integer>{
    
}
