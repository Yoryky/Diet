package com.yoryky.diet.event;

/**
 * Created by caicai on 2017/9/3.
 */

public class MeUpdateEvent {
    private String message;
    public MeUpdateEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
