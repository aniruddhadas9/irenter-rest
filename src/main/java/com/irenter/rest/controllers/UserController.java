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

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public Entity get(@PathVariable("userId") String userId) {
        return userService.get(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Entity post(@RequestBody User user) {
        return userService.post(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Message put(@RequestBody User user) {
        return userService.put(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Message delete(@PathVariable("userId") String userId) {
        return userService.delete(userId);
    }

}
