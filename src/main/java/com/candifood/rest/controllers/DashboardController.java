package com.candifood.rest.controllers;

import java.util.Collection;

import com.candifood.rest.entities.Restaurant;
import com.candifood.rest.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DashboardController
{
    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Restaurant> gets()
    {
        return restaurantService.get();
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.GET)
    public Restaurant get(@PathVariable("objectId") String objectId) {
        return restaurantService.get(objectId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Restaurant post(@RequestBody Restaurant restaurant)
    {
        return restaurantService.post(restaurant);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Restaurant put(@RequestBody Restaurant restaurant)
    {
        return restaurantService.put(restaurant);
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    public Restaurant delete(@PathVariable("objectId") String objectId)
    {
        return restaurantService.delete(objectId);
    }

}
