package com.talhaguller.todolist.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (String message){
        super(message);
    }

}
