package com.irenter.rest.services.impls;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import com.irenter.rest.entities.Rent;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.RentService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class RentServiceImpl implements RentService {

    private final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);

    @Autowired
    Datastore datastore;

    private KeyFactory rentKeyFactory;

    @PostConstruct
    public void initializeKeyFactories() {
        log.info("Initializing key factories");
        System.out.println("Initializing key factories");
        rentKeyFactory = datastore.newKeyFactory().setKind("Rent");
    }

    public Entity createUser(Rent rent) {
        return datastore.put(createRentEntity(rent));
    }

    public Batch.Response createUser(List<Rent> rents) {
        Batch batch = datastore.newBatch();
        for (Rent rent : rents) {
            batch.put(createRentEntity(rent));
        }

        return batch.submit();
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
            rent.setOwner(entity.getString("email"));
            rent.setRenter(entity.getString("fullName"));
            rents.add(rent);
        }
        return rents;
    }

    @Override
    public Entity get(String id) {
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Rent");
        Key key = keyFactory.newKey(id);
        Entity entity = datastore.get(key);
        return entity;
    }

    @Override
    public Entity post(Rent rent) {
        return datastore.put(this.createRentEntity(rent));
    }

    @Override
    public Rent put(Rent rent) {
        datastore.update(this.createRentEntity(rent));
        return rent;
    }

    @Override
    public Rent delete(String id) {
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Rent");
        Key key = keyFactory.newKey(id);
        datastore.delete(key);
        return new Rent();
    }

    private Entity createRentEntity(Rent rent) {
        Key key = rentKeyFactory.newKey(rent.getId());
        return Entity.newBuilder(key)
                .set("owner", rent.getOwner())
                .set("renter", rent.getRenter())
                .set("start", rent.getStart().toString())
                .set("end", rent.getEnd().toString())
                .set("created", new TimestampValue(Timestamp.now()))
                .set("modified", new TimestampValue(Timestamp.now()))
                // .set("address", rent.getAddress())
                .build();
    }
    @Async
    public void updateUser(String id, Rent rent) {
        //
    }

    @Async
    public void deleteUser(String id) {
        //
    }
}
