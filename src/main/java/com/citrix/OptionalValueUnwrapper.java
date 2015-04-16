package com.citrix;

import org.hibernate.validator.spi.valuehandling.ValidatedValueUnwrapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalValueUnwrapper extends ValidatedValueUnwrapper<Optional<?>> {

    @Override
    public Object handleValidatedValue(Optional<?> value) {
        return value == null ? null : value.orElse(null);
    }

    @Override
    public Type getValidatedValueType(Type valueType) {
        ParameterizedType parameterizedType = (ParameterizedType)valueType;
        return parameterizedType.getActualTypeArguments()[0];
    }

}