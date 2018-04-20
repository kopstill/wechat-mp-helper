package com.kopever.wechat.dao;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kopever.wechat.common.util.Jackson;
import com.kopever.wechat.common.util.RandomUtil;
import com.kopever.wechat.domain.data.User;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.*;

/**
 * Created by Lullaby on 2018/2/6
 */
public class MongodbTest {

    @Test
    public void testClient() {
        MongoClientURI connectionString = new MongoClientURI("mongodb://112.74.169.188:27017");
        MongoClient mongoClient = new MongoClient(connectionString);
        Collection<DB> databases = mongoClient.getUsedDatabases();
        System.out.println(databases);
        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection("test");
    }

    @Test
    public void testInsert() {
//        MongoClientURI connectionString = new MongoClientURI("mongodb://112.74.169.188:27017");
        MongoCredential credential = MongoCredential.createCredential("test", "test", "7ef5e5940cac11e88a9400163e02b6ed".toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress("112.74.169.188", 27017), Collections.singletonList(credential));
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("temp");

        Document document = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(document);

        System.out.println(collection.count());

        User user = new User();
        user.setId(RandomUtil.getRandomAllChar(32));
        user.setUsername("kop");
        user.setEmail("kop.still@gmail.com");
        user.setAge(Byte.valueOf("29"));
        user.setGender('M');
        List<String> addresses = Lists.newArrayList();
        addresses.add("四川广安");
        addresses.add("广东广州");
        user.setAddresses(addresses);
        Map<String, Object> children = Maps.newHashMap();
        User.Child boy = new User.Child();
        boy.setName("Walle");
        boy.setGender('M');
        boy.setAge(Byte.valueOf("10"));
        User.Child girl = new User.Child();
        girl.setName("Eve");
        girl.setGender('F');
        girl.setAge(Byte.valueOf("7"));
        children.put("boy", boy);
        children.put("girl", girl);
        user.setChildren(children);

        Map map = Jackson.fromObject(user, Map.class);
        collection.insertOne(new Document(map));

        System.out.println(collection.count());
    }

}
