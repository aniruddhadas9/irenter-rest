package com.irenter.rest.services;

import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Message;
import com.irenter.rest.entities.User;
import com.irenter.rest.entities.User;

import java.awt.*;
import java.util.List;

public interface UserService {

    public List<User> getByName(User user);

    public List<User> getByEmail(User user);

    public Entity get(String id);

    public Entity post(User user);

    public Message put(User user);

    public Message delete(String id);

}
