package com.candifood.rest.services.impls;

import com.candifood.rest.entities.Restaurant;
import com.candifood.rest.services.RestaurantService;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RestaurantServiceImpl implements RestaurantService{

    private static Map<Integer, Restaurant> restaurants;
    static {
        restaurants = new HashMap<Integer, Restaurant>() {
            {
                put(1, new Restaurant("name", "address", "email", "phone"));
                put(2, new Restaurant("name1", "address1", "email1", "phone1"));
                put(3, new Restaurant("name2", "address2", "email2", "phone2"));
                put(4, new Restaurant("name3", "address3", "email3", "phone3"));
            }
        };
    }

    @Override
    public Collection<Restaurant> getRestaurants() {
        return this.restaurants.values();
    }
}
