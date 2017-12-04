package com.irenter.rest.controllers;

import java.util.Collection;

import com.irenter.rest.entities.Rent;
import com.irenter.rest.services.RentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Dashboard")
@RequestMapping("/")
public class DashboardController
{
    @Autowired
    RentService rentService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Rent> gets()
    {
        return rentService.get();
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.GET)
    public Rent get(@PathVariable("objectId") String objectId) {
        return rentService.get(objectId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Rent post(@RequestBody Rent rent)
    {
        return rentService.post(rent);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Rent put(@RequestBody Rent rent)
    {
        return rentService.put(rent);
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    public Rent delete(@PathVariable("objectId") String objectId)
    {
        return rentService.delete(objectId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Rent add()
    {
        Rent rent = new Rent();
        return rentService.post(rent);
    }

}