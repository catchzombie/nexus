package com.catchzombie.aerospike.impl;

import com.catchzombie.aerospike.CzRecord;
import com.catchzombie.utils.Constants;
import com.sd.aerospike.common.annotations.StorableBin;
import com.sd.aerospike.common.annotations.StorableRecord;
import com.sd.aerospike.common.enums.StorableType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by shubham on 8/2/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@StorableRecord(setname = Constants.USERCATEGORYMAP_SETNAME,namespace = Constants.CZ_NAMESPACE)
public class UserCategoryMap extends CzRecord {

    @StorableBin(name = "userId",type = StorableType.STRING)
    private String userId;

    @StorableBin(name = "selfCategory",type = StorableType.SET)
    private Set<String> selfCategories;

    @StorableBin(name = "wantCategory",type = StorableType.SET)
    private Set<String> wantCategories;

    @Override
    public String getAerospikeKey() {
        return buildAerospikeKey(userId);
    }

    @Override
    public void setAerospikeKey(String s) {
        userId=s.trim();
    }

    public static String buildAerospikeKey(String userId){
        return userId;
    }
}
