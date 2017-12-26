package com.irenter.rest.services;

import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Message;
import com.irenter.rest.entities.User;

import java.util.List;

public interface UserService {
    List<User> getByName(User user);
    List<User> getByEmail(User user);
    Entity get(String userId);
    Entity post(User user);
    Message put(User user);
    Message delete(String id);
}
