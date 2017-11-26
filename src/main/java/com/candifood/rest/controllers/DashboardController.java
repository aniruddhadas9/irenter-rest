package com.candifood.rest.controllers;

import java.util.Collection;

import com.candifood.rest.entities.Restaurant;
import com.candifood.rest.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DashboardController
{
    @Autowired
    RestaurantService restaurantService;

    @RequestMapping()
    public Collection<Restaurant> home()
    {
        return restaurantService.getRestaurants();
    }

}
