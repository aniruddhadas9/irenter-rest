package com.irenter.rest.controllers;

import java.util.Collection;

import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/home")
public class HomeController implements ErrorController {

    @Autowired
    RentService rentService;

    private static final String PATH = "/error";

    @RequestMapping()
    public Collection<Rent> home() {
        User user = new User();
        user.setId("1");
        return rentService.getByUser(user);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
