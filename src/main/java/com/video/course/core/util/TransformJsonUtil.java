package com.video.course.core.util;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransformJsonUtil {


    private static final ObjectMapper objectMapper = new ObjectMapper();

    public TransformJsonUtil() {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static String toJsonString(Object o) {
        String jsonString = null;
        try {
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            jsonString = objectMapper.writer().writeValueAsString(o);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return jsonString;
    }

    public static <T> T jsonStringToObject(Class<T> theClass, String s) {
        T data = null;
        try {
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            data = objectMapper.readValue(s, theClass);
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return data;
    }

}
