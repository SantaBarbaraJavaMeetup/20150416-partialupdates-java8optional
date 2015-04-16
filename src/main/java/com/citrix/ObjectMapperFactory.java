package com.citrix;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

@Component("objectMapper")
public class ObjectMapperFactory extends AbstractFactoryBean<ObjectMapper> {

    @Override
    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    @Override
    protected ObjectMapper createInstance() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper;
    }

}
