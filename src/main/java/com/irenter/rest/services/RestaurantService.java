package com.irenter.rest.services;

import java.util.Collection;

import com.irenter.rest.entities.Rent;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {

    public Collection<Rent> get();
    public Rent get(String id);
    public Rent post(Rent rent);
    public Rent put(Rent rent);
    public Rent delete(String id);
}
