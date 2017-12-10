package com.irenter.rest.controllers;


import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Message;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "User")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
    public Entity get(@PathVariable("addressId") String rentId) {
        return userService.get(rentId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Entity post(@RequestBody User rent) {
        return userService.post(rent);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Message put(@RequestBody User rent) {
        return userService.put(rent);
    }

    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("objectId") String objectId) {
        return userService.delete(objectId);
    }

}
