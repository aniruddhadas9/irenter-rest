package com.irenter.rest.services;

import java.util.List;

import com.google.cloud.datastore.Entity;
import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import org.springframework.stereotype.Service;


@Service
public interface RentService {

    public List<Rent> getByUser(User user);
    public Entity get(String id);
    public Entity post(Rent rent);
    public Rent put(Rent rent);
    public Rent delete(String id);
}
