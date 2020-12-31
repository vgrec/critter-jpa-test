package com.udacity.jdnd.course3.critter.service;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Item with specified it could not be found.")
public class ItemNotFoundException extends Exception {
    ItemNotFoundException(Long itemId) {
        super("The item with id " + itemId + " could not be found in database");
    }
}
