/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.flightapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flightapp.service.FlightService;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author HParaense
 */
@Controller
public class FlightController {
    //@Value("${spring.application.name}")
  
    String appName;
    private static final SimpleDateFormat TIMEFORMAT = new SimpleDateFormat("HH:mm:ss");
    private static final Logger LOG = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightService flightService;
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
            return builder.build();
    }
    
//    @GetMapping("/")
//    public RedirectView redirectWithUsingRedirectView() {
//        return new RedirectView("/flights");
//    }
    
    @GetMapping("/flights")
    public String flightsPage(Model model, RestTemplate restTemplate) {
        model.addAttribute("appName", appName);
        model.addAttribute("flights", flightService.showAll());

        return "flights";
    }
    
    @GetMapping("/showTap")
    public String show(Model model, RestTemplate restTemplate, @RequestParam(value = "company", defaultValue = "") String company) throws JsonProcessingException {
        model.addAttribute("appName", appName);
        model.addAttribute("show", flightService.showByAirlineCompany(company));

        return "show";
    }
    
    @RequestMapping(value = "/delete/{id}")
    private String deleteFlight(@PathVariable(name = "id") String id){
        LOG.info("Deleted flight of id " + id);
        flightService.delete(id);
        return "redirect:/flights";
    }
    
    @RequestMapping(value = "/deleteAll")
    private String deleteFlight(){
        LOG.info("Deleted all flights.");
        flightService.deleteAll();
        return "redirect:/flights";
    }
    
}
