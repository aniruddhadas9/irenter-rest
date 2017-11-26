package com.candifood.rest.services;

import com.candifood.rest.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface RestaurantService {

    public Collection<Restaurant> getRestaurants();
}
