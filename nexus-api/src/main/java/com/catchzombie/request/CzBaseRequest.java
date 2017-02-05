package com.catchzombie.request;

import com.catchzombie.utils.Tuple;

/**
 * Created by shubham on 4/2/17.
 */
public abstract class CzBaseRequest {

    public abstract Tuple<Boolean, String> valid();
}
