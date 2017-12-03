package com.irenter.rest.services.impls;

import com.irenter.rest.entities.Rent;
import com.irenter.rest.services.RestaurantService;
import com.google.cloud.datastore.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class RestaurantServiceImpl implements RestaurantService {

    private static Map<Integer, Rent> restaurants;



    static {
        restaurants = new HashMap<Integer, Rent>() {
            {
                put(1, new Rent("name", "address", "email", "phone"));
                put(2, new Rent("name1", "address1", "email1", "phone1"));
                put(3, new Rent("name2", "address2", "email2", "phone2"));
                put(4, new Rent("name3", "address3", "email3", "phone3"));
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
        userKeyFactory = datastore.newKeyFactory().setKind("Rent");
    }

    public Entity createUser(Rent rent) {
        return datastore.put(createRestaurantEntity(rent));
    }

    public Batch.Response createUser(List<Rent> rents) {
        Batch batch = datastore.newBatch();
        for (Rent rent : rents) {
            batch.put(createRestaurantEntity(rent));
        }

        return batch.submit();
    }

    private Entity createRestaurantEntity(Rent rent) {
        Key key = userKeyFactory.newKey(rent.getId());
        return Entity.newBuilder(key)
            .set("email", rent.getEmail())
            .set("name", rent.getName())
            .set("phone", rent.getPhone())
            .build();
    }
    @Override
    public Collection<Rent> get()
    {
        return this.restaurants.values();
    }


    public Rent addRestaurant(Rent rent) {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Key taskKey;
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("rent");
        IncompleteKey key = keyFactory.setKind("visit").newKey();
        keyFactory = datastore.newKeyFactory().setKind("rent");
        taskKey = keyFactory.newKey("rent");
        Entity task = Entity.newBuilder(taskKey)
                .set("category", "Personal")
                .set("done", false)
                .set("priority", 4)
                .set("description", "Learn Cloud Datastore")
                .build();
        return rent;
    }

    @Override
    public Rent get(String id)
    {
        return new Rent("name", "address", "email", "phone");
    }

    @Override
    public Rent post(Rent rent)
    {
        return rent;
    }

    @Override
    public Rent put(Rent rent)
    {
        return rent;
    }

    @Override
    public Rent delete(String id)
    {
        return new Rent("name", "address", "email", "phone");
    }
}
