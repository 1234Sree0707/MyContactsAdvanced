package com.seveneleven.factory;

import java.util.HashMap;
import java.util.Map;

import com.seveneleven.model.Tag;

public class TagFactory {

    private static Map<String, Tag> tagPool = new HashMap<>();

    public static Tag getTag(String name) {

        name = name.toLowerCase();

        if(!tagPool.containsKey(name)){
            tagPool.put(name, new Tag(name));
        }

        return tagPool.get(name);
    }
}