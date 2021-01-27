package com.nullspace.textsharecore;

import org.bson.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
public class HTTPHandler {

    @GetMapping(path = "api/about")
    public static ResponseEntity<Map<String, String>> about(){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", LocalDateTime.now().toString());
        map.put("version", TextshareCoreApplication.version);
        map.put("texts", "0");
        map.put("about", TextshareCoreApplication.about);
        return ResponseEntity.ok(map);
    }

    @PostMapping(path = "api/new")
    public static ResponseEntity<String> createText(@RequestBody String text){
        Map<String, Object> map = new HashMap<>();
        String id = HandyUtils.generateRandomID();
        map.put("_id", id);
        map.put("text", text);
        if (MongoHandler.createDocument(map)){
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.status(500).body("Failed to create entry in DB");
        }

    }

    @GetMapping(path = "api/{id}")
    public static ResponseEntity<String> getText(@PathVariable String id){
        Document d = MongoHandler.getDocument(id);
        return d != null ? ResponseEntity.ok(d.toJson()) : ResponseEntity.status(404).body("The requested text does not exist");
    }


}
