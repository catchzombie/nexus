package com.catchzombie.aerospike;

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
 * Created by shubham on 5/2/17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@StorableRecord(setname = Constants.USERBIO_SETNAME,namespace = Constants.CZ_NAMESPACE)
public class ProfileBio extends CzRecord {
    @StorableBin(name="userId",type = StorableType.STRING)
    private String userId;

    @StorableBin(name = "bio",type = StorableType.STRING)
    private String userBio;

    @StorableBin(name = "imageLinks",type = StorableType.SET)
    private Set<String> userImageUrls;

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
