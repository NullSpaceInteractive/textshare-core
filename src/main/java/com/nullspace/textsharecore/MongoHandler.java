package com.nullspace.textsharecore;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.*;
import org.bson.*;

import java.util.*;

public class MongoHandler {

    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> texts;

    public static void connectToMongo(String token){
        System.out.println("Connecting to Mongo");
        mongoClient = MongoClients.create(token);
        database = mongoClient.getDatabase("textshareDB");
        texts = database.getCollection("texts");
    }


    public static TextEntry getDocument(String id){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(texts.find(Filters.eq("_id", id)).first().toJson(), TextEntry.class);
        } catch (Exception e){
            return null;
        }
    }

    public static boolean createDocument(Map<String, Object> content){
        return texts.insertOne(new Document(content)).wasAcknowledged();
    }
}
