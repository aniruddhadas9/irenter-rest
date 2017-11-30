package com.candifood.rest.services.impls;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.candifood.rest.entities.Restaurant;
import com.candifood.rest.services.RestaurantService;
import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    private final Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    Datastore datastore;

    private KeyFactory userKeyFactory;

    @PostConstruct
    public void initializeKeyFactories() {
        log.info("Initializing key factories");
        userKeyFactory = datastore.newKeyFactory().setKind("Restaurant");
    }

    public Entity createUser(Restaurant restaurant) {
        return datastore.put(createRestaurantEntity(restaurant));
    }

    public Batch.Response createUser(List<Restaurant> restaurants) {
        Batch batch = datastore.newBatch();
        for (Restaurant restaurant : restaurants) {
            batch.put(createRestaurantEntity(restaurant));
        }

        return batch.submit();
    }

    private Entity createRestaurantEntity(Restaurant restaurant) {
        Key key = userKeyFactory.newKey(restaurant.getId());
        return Entity.newBuilder(key)
            .set("email", restaurant.getEmail())
            .set("name", restaurant.getName())
            .set("phone", restaurant.getPhone())
            .build();
    }
    @Override
    public Collection<Restaurant> get()
    {
        return this.restaurants.values();
    }

    @Override
    public Restaurant get(String id)
    {
        return new Restaurant("name", "address", "email", "phone");
    }

    @Override
    public Restaurant post(Restaurant restaurant)
    {
        return restaurant;
    }

    @Override
    public Restaurant put(Restaurant restaurant)
    {
        return restaurant;
    }

    @Override
    public Restaurant delete(String id)
    {
        return new Restaurant("name", "address", "email", "phone");
    }
}
