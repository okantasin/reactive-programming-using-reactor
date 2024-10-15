package com.reactiveprogrammingusingreactor.exception;

public class ReviewsClientException extends RuntimeException{
    private String message;

    public ReviewsClientException(String message) {
        super(message);
        this.message = message;
    }
}
