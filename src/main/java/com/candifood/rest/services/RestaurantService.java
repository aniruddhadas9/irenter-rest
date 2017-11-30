package com.candifood.rest.services;

import java.util.Collection;

import com.candifood.rest.entities.Restaurant;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {

    public Collection<Restaurant> get();
    public Restaurant get(String id);
    public Restaurant post(Restaurant restaurant);
    public Restaurant put(Restaurant restaurant);
    public Restaurant delete(String id);
}
