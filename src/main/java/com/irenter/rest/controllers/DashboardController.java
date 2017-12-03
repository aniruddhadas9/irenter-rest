package com.irenter.rest.controllers;

import java.util.Collection;

import com.irenter.rest.entities.Rent;
import com.irenter.rest.services.RestaurantService;
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
    public Collection<Rent> gets()
    {
        return restaurantService.get();
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.GET)
    public Rent get(@PathVariable("objectId") String objectId) {
        return restaurantService.get(objectId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Rent post(@RequestBody Rent rent)
    {
        return restaurantService.post(rent);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Rent put(@RequestBody Rent rent)
    {
        return restaurantService.put(rent);
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    public Rent delete(@PathVariable("objectId") String objectId)
    {
        return restaurantService.delete(objectId);
    }

    @RequestMapping("add")
    public Rent add()
    {
        Rent rent = new Rent();
        return restaurantService.post(rent);
    }

}
