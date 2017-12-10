package com.irenter.rest.controllers;

import java.util.Collection;

import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Message;
import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.RentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Dashboard")
@RequestMapping("/")
public class DashboardController implements ErrorController {

    private static final String PATH = "/error";
    
    @Autowired
    RentService rentService;

    @RequestMapping(method = RequestMethod.GET)
    public Message buildInfo() {
        return new Message("I renter is renting service for renter and owner of properties");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
