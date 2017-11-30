package com.candifood.rest.controllers;

import java.util.Collection;

import com.candifood.rest.entities.Restaurant;
import com.candifood.rest.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/home")
public class HomeController implements ErrorController {

    @Autowired
    RestaurantService restaurantService;

    private static final String PATH = "/error";

    @RequestMapping()
    public Collection<Restaurant> home() {
        return restaurantService.get();
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
