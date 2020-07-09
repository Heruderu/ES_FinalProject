/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightapp.repository;

import com.flightapp.model.Flight;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author HParaense
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class FlightRepositoryIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private FlightRepository flightRepository;
    
    @Test
    public void whenFindByAirline_thenReturnFlight() {
        // given
        Flight tap = new Flight(1, new Date(), new Date(), "TAP0001", "LIS", "GRU", 0);
        entityManager.persist(tap);
        entityManager.flush();

        // when
        Flight found = flightRepository.findByAirline(tap.getAirline());

        // then
        assertThat(found.getAirline())
          .isEqualTo(tap.getAirline());
    }
}
