package com.udacity.jdnd.course3.critter.service;

public class ItemNotFoundException extends Exception {
    ItemNotFoundException(Long itemId) {
        super("The item with id " + itemId + " could not be found in database");
    }
}
