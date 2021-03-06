package com.irenter.rest.controllers;

import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.RentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Api(tags = "Rent")
@RequestMapping("/rent")
public class RentController {
    @Autowired
    RentService rentService;

    @RequestMapping(value = "/owner/{ownerId}", method = RequestMethod.GET)
    public Collection<Rent> getRentsByOwner(@PathVariable("ownerId") String ownerId) {
        User user = new User();
        user.setId(ownerId);

        return rentService.getByUser(user);
    }

    @RequestMapping(value = "/renter/{renterId}", method = RequestMethod.GET)
    public Collection<Rent> getRentsByRenter(@PathVariable("renterId") String renterId) {
        User user = new User();
        user.setId(renterId);

        return rentService.getByUser(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Entity post(@RequestBody Rent rent) {
        return rentService.post(rent);
    }

    @RequestMapping(value = "/{rentId}", method = RequestMethod.GET)
    public Entity get(@PathVariable("rentId") String rentId) {
        return rentService.get(rentId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Rent put(@RequestBody Rent rent) {
        return rentService.put(rent);
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    public Rent delete(@PathVariable("objectId") String objectId) {
        return rentService.delete(objectId);
    }
}
