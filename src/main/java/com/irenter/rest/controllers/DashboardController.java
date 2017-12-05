package com.irenter.rest.controllers;

import java.util.Collection;

import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
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

    @RequestMapping(value = "/all/{objectId}", method = RequestMethod.GET)
    public Collection<Rent> gets(@PathVariable("objectId") String userId)
    {
        User user = new User();
        user.setId(userId);

        return rentService.getByUser(user);
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.GET)
    public Entity get(@PathVariable("objectId") String objectId) {
        return rentService.get(objectId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Entity post(@RequestBody Rent rent)
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
}
