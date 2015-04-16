package com.citrix;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class DocumentController {

    private Document document = new Document();

    @RequestMapping(value = "/document", method = RequestMethod.GET)
    public @ResponseBody Document get() {
        return document;
    }

    @RequestMapping(value = "/document", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void set(@Valid @RequestBody Document doc) {
        Optional.ofNullable(doc.getKey()).ifPresent(document::setKey);
        Optional.ofNullable(doc.getContent()).ifPresent(document::setContent);
    }

    @ExceptionHandler
    public ResponseEntity<Map> handleException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        Map<String, String> map = new HashMap<>();
        map.put("code", fieldError.getDefaultMessage());
        map.put("field", fieldError.getField());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

}
