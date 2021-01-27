package com.nullspace.textsharecore;

import com.fasterxml.jackson.core.*;

import java.util.*;

public class HandyUtils {

    public static String generateRandomID() {
        int[] randomNums = new int[6];
        for (int i = 0; i < randomNums.length; i++) {
            randomNums[i] = (int) (Math.random() * 62);
        }

        String id = "";

        for (int i = 0; i < randomNums.length; i++) {
            if (randomNums[i] < 10){
                id += (char) (randomNums[i] + 48);
            } else if (randomNums[i] < 36){
                id += (char) (randomNums[i] + 87);
            } else {
                id += (char) (randomNums[i] + 29);
            }
        }

        if (MongoHandler.getDocument(id) == null){
            return id;
        } else {
            return generateRandomID();
        }
    }

}
