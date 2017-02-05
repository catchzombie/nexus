package com.catchzombie.resource;

import com.catchzombie.request.CzBaseRequest;
import com.catchzombie.utils.Tuple;
import com.google.common.base.Strings;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 * Created by shubham on 4/2/17.
 */
public abstract class CzBaseResource {
    protected void validateRequest(CzBaseRequest request){
        Tuple<Boolean, String> validation = request.valid();
        if(validation== null || validation.getFirst() == null || !validation.getFirst()){
            throw new WebApplicationException(validation.getSecond(),
                    Response.status(Response.Status.BAD_REQUEST).entity(validation.getSecond()).build());
        }
    }

    protected void validateNonNullRequestParams(Object ...params){
        boolean valid = true;
        if(params!=null){
            for(Object param : params){
                if(param == null){
                    valid = false;
                }
            }
        }
        if(!valid){
            String message = "Null request params";
            throw new WebApplicationException(message,
                    Response.status(Response.Status.BAD_REQUEST).entity(message).build());
        }
    }

    protected void validateNonEmptyRequestParams(String ...params){
        boolean valid = true;
        if(params!=null){
            for(String param : params){
                if(Strings.isNullOrEmpty(param)){
                    valid = false;
                }
            }
        }
        if(!valid){
            String message = "Null/empty request params";
            throw new WebApplicationException(message,
                    Response.status(Response.Status.BAD_REQUEST).entity(message).build());
        }
    }

}
