package com.irenter.rest.services.impls;

import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.RentService;
import com.google.cloud.datastore.*;
import org.springframework.stereotype.Repository;

import java.util.*;

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
public class RentServiceImpl implements RentService {

    private final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);

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
    public List<Rent> getByUser(User user) {
        List<Rent> rents = new ArrayList<Rent>();
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("Rent")
                .setOrderBy(StructuredQuery.OrderBy.desc("created"))
                .build();
        QueryResults<Entity> tasks = datastore.run(query);
        while (tasks.hasNext()) {
            Entity entity = tasks.next();
            System.out.println(entity);
            Rent rent = new Rent();
            rent.setId(entity.getKey().getName());
            rent.setEmail(entity.getString("email"));
            rent.setFullName(entity.getString("fullName"));
            rent.setPassword(entity.getString("password"));
            rents.add(rent);
        }
        return users;
    }


    public Rent addRent(Rent rent) {
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
    public Rent get(String id) {
        return new Rent("name", "address", "email", "phone");
    }

    @Override
    public Rent post(Rent rent) {
        return this.addRent(rent);
    }

    @Override
    public Rent put(Rent rent) {
        return rent;
    }

    @Override
    public Rent delete(String id) {
        return new Rent("name", "address", "email", "phone");
    }
}
