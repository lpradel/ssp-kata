package com.lukaspradel.ssp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.inject.Produces;

public class ObjectMapperProducer {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    @Produces
    public ObjectMapper produceObjectMapper() {
        return OBJECT_MAPPER;
    }
}
