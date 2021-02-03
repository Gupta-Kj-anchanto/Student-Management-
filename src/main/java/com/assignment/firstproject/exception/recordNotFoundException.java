package com.assignment.firstproject.exception;

public class recordNotFoundException extends RuntimeException {

    public recordNotFoundException(String record, Long id) {
        super(record + " with id " + id + " not found in database.");
    }

}