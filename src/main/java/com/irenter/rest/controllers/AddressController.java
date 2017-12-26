package com.irenter.rest.controllers;

import com.google.cloud.datastore.Entity;
import com.google.gson.Gson;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.GoogleMapService;
import com.irenter.rest.services.RentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@Api(tags = "Address")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    RentService rentService;

    @Autowired
    GoogleMapService googleMapService;

    @RequestMapping(value = "/locality/{locality}", method = RequestMethod.GET)
    public Collection<Rent> getRentsByOwner(@PathVariable("locality") String ownerId) {
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

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    public Entity get(@PathVariable("addressId") String rentId) {
        return rentService.get(rentId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Entity post(@RequestBody Rent rent) {
        return rentService.post(rent);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Rent put(@RequestBody Rent rent) {
        return rentService.put(rent);
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    public Rent delete(@PathVariable("objectId") String objectId) {
        return rentService.delete(objectId);
    }

    // experimental and will be deleted
    @RequestMapping(value = "/nearbyplace", method = RequestMethod.GET)
    public AddressComponent getNearByPlace() throws InterruptedException, ApiException, IOException {
        return googleMapService.nearByPlaces();
    }

    @RequestMapping(value = "/geocode", method = RequestMethod.GET)
    public Gson getGeocoding() throws InterruptedException, ApiException, IOException {
        return googleMapService.geocodingApiData("1600 Amphitheatre Parkway Mountain View, CA 94043");
    }
}

