package com.irenter.rest.services.impls;

import java.util.List;

import javax.annotation.PostConstruct;

import com.irenter.rest.entities.User;
import com.google.cloud.datastore.Batch;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    Datastore datastore;

    private KeyFactory userKeyFactory;

    @PostConstruct
    public void initializeKeyFactories()
    {
        log.info("Initializing key factories");
        userKeyFactory = datastore.newKeyFactory().setKind("User");
    }

    public Entity createUser(User user)
    {
        return datastore.put(createUserEntity(user));
    }

    public Batch.Response createUser(List<User> users)
    {
        Batch batch = datastore.newBatch();
        for (User user : users)
        {
            batch.put(createUserEntity(user));
        }

        return batch.submit();
    }

    private Entity createUserEntity(User user)
    {
        Key key = userKeyFactory.newKey(user.getId());
        return Entity.newBuilder(key).set("email", user.getEmail()).set("password", user.getPassword()).set("fistName", user.getFirstName())
            .set("dob", user.getDob()).build();
    }
}