package com.citrix;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.internal.engine.ConfigurationImpl;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;

@Component("validator")
public class ValidatorFactoryBean extends AbstractFactoryBean<Validator> {

    @Override
    public Class<?> getObjectType() {
        return Validator.class;
    }

    @Override
    protected Validator createInstance() throws Exception {
        ConfigurationImpl configuration = (ConfigurationImpl)Validation.byProvider(HibernateValidator.class)
                .configure();

        /**
         * remove problematic {@link org.hibernate.validator.internal.engine.valuehandling.OptionalValueUnwrapper} that suffers from NPE bug
         * https://hibernate.atlassian.net/browse/HV-977
         */
        configuration.getValidatedValueHandlers().clear();

        return configuration
                .addValidatedValueHandler(new OptionalValueUnwrapper())
                .buildValidatorFactory()
                .getValidator();
    }

}
