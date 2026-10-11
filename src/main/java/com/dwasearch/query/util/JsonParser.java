package com.dwasearch.query.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
public class JsonParser {
    public static <T> List<T> parseJsonToList(String json, Class<T> targetClass){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(json, new TypeReference<List<T>>(){});
        }
        catch (JsonProcessingException e){
            System.err.println("List parse error: " + e.getMessage());
            return null;
        }
    }
    public static <T> T parseJsonToObject(String json, Class<T> targetClass){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, targetClass);
        }catch (JsonProcessingException e){
            System.err.println("Object parse error: " + e.getMessage());
            return null;
        }
    }
}
