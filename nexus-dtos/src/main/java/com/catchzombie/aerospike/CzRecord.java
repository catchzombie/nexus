package com.catchzombie.aerospike;

import com.sd.aerospike.common.records.impl.AerospikeRecordStringKey;

/**
 * Created by shubham on 5/2/17.
 */
public abstract class CzRecord implements AerospikeRecordStringKey {
    public abstract String getAerospikeKey();

    public abstract void setAerospikeKey(String s);

    public Class<String> getAerospikeKeyType() {
        return String.class;
    }

    }



