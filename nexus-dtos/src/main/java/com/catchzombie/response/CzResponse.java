package com.catchzombie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by shubham on 4/2/17.
 */
@Getter
@Setter
@AllArgsConstructor
public class CzResponse<T> {
    private ResponseStatus status;

    private String message;

    private T data;

    public CzResponse(ResponseStatus status) {
        this.status = status;
    }

    public CzResponse(ResponseStatus status,String message){
        this.status=status;
        this.message=message;
    }

    public CzResponse(ResponseStatus status,T data){
        this.status=status;
        this.data=data;
    }
}
