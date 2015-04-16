package com.citrix;

import org.hibernate.validator.valuehandling.UnwrapValidatedValue;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Optional;

public class Document {

    @UnwrapValidatedValue
    @Min(value = 0, message = "minimum")
    private Optional<Long> key;
    @UnwrapValidatedValue
    @Pattern(regexp = "[a-zA-Z0-9 ]+", message = "invalid")
    private Optional<String> content;

    public Optional<Long> getKey() {
        return key;
    }

    public void setKey(Optional<Long> key) {
        this.key = key;
    }

    public Optional<String> getContent() {
        return content;
    }

    public void setContent(Optional<String> content) {
        this.content = content;
    }
}
