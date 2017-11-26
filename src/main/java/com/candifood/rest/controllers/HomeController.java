package com.candifood.rest.controllers;

import com.candifood.rest.entities.Restaurant;
import com.candifood.rest.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController()
@RequestMapping("/home")
public class HomeController implements ErrorController {

    @Autowired
    RestaurantService restaurantService;

    private static final String PATH = "/error";

    @RequestMapping()
    public Collection<Restaurant> home() {
        return restaurantService.getRestaurants();
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
