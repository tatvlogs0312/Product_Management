package com.example.demo.exception;

import java.util.function.Supplier;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){
        super(msg);
    }
}
