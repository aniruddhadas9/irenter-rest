package com.irenter.rest.services;

import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface RentService {
    List<Rent> getByUser(User user);
    Entity get(String id);
    Entity post(Rent rent);
    Rent put(Rent rent);
    Rent delete(String id);
}
