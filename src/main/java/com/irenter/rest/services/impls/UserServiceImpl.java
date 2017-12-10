package com.irenter.rest.services.impls;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.*;
import com.irenter.rest.entities.BatchUser;
import com.irenter.rest.entities.Message;
import com.irenter.rest.entities.User;
import com.irenter.rest.services.UserService;
import org.springframework.scheduling.annotation.Async;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    Datastore datastore;

    private KeyFactory userKeyFactory;

    @PostConstruct
    public void initializeKeyFactories() {
        log.info("Initializing key factories");
        userKeyFactory = datastore.newKeyFactory().setKind("User");
    }


    @Override
    public List<User> getByName(User inputUser) {
        List<User> users = new ArrayList<User>();
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("User")
                .setFilter(
                        StructuredQuery.CompositeFilter.and(
                                StructuredQuery.PropertyFilter.eq("firstName", inputUser.getFirstName()),
                                StructuredQuery.PropertyFilter.ge("lastName", inputUser.getLastName())))
                .setOrderBy(StructuredQuery.OrderBy.desc("firstName"))
                .build();
        QueryResults<Entity> tasks = datastore.run(query);
        while (tasks.hasNext()) {
            Entity entity = tasks.next();
            System.out.println(entity);
            User user = new User();

            user.setId(entity.getKey().getName());
            user.setEmail(entity.getString("email"));
            user.setFirstName(entity.getString("firstName"));
            user.setLastName(entity.getString("lastName"));
            users.add(user);
        }
        return users;
    }


    @Override
    public List<User> getByEmail(User inputUser) {
        List<User> users = new ArrayList<User>();
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("User")
                .setFilter(
                        StructuredQuery.CompositeFilter.and(
                                StructuredQuery.PropertyFilter.eq("email", inputUser.getEmail())))
                .setOrderBy(StructuredQuery.OrderBy.desc("firstName"))
                .build();
        QueryResults<Entity> tasks = datastore.run(query);
        while (tasks.hasNext()) {
            Entity entity = tasks.next();
            System.out.println(entity);
            User user = new User();

            user.setId(entity.getKey().getName());
            user.setEmail(entity.getString("email"));
            user.setFirstName(entity.getString("firstName"));
            user.setLastName(entity.getString("lastName"));
            user.setPassword(entity.getString("password"));
            users.add(user);
        }
        return users;
    }

    @Override
    public Entity get(String id) {
        return null;
    }

    @Override
    public Entity post(User user) {
        return datastore.put(createUserEntity(user));
    }

    @Override
    public Message put(User user) {
        datastore.update(createUserEntity(user));
        return new Message("User updated in datastore");
    }

    @Override
    public Message delete(String id) {
        User user = new User();
        user.setId(id);
        Key key = userKeyFactory.newKey(user.getId());
        datastore.delete(key);
        return new Message("User deleted from datastore");
    }

    public Batch.Response createUser(BatchUser users) {
        Batch batch = datastore.newBatch();
        for (User user : users.getUsers()) {
            batch.put(createUserEntity(user));
        }

        return batch.submit();
    }

    private Entity createUserEntity(User user) {
        Key key = userKeyFactory.newKey(user.getId());
        return Entity.newBuilder(key)
                .set("email", user.getEmail())
                .set("password", user.getPassword())
                .set("firstName", user.getFirstName())
                .set("lastName", user.getLastName())
                .set("age", user.getAge())
                .build();
    }

    @Async
    public void updateUser(String id, User user) {
        //
    }

    @Async
    public void deleteUser(String id) {
        //
    }


}
