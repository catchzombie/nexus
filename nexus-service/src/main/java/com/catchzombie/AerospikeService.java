package com.catchzombie;

import com.sd.aerospike.client.SDAerospikeClient;
import com.sd.aerospike.client.exceptions.OperationException;
import com.sd.aerospike.common.records.AerospikeRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by shubham on 5/2/17.
 */
@Service
public class AerospikeService {
    private SDAerospikeClient client;

    private static final Logger LOGGER = LoggerFactory.getLogger(AerospikeService.class);

    //TODO
    public AerospikeService(){
        client=new SDAerospikeClient("localhost",3000); //remove hardcoding
    }

    public  <V, T extends AerospikeRecord<V>> T  get(Class<T> clazz, V key) {
        try {
            return client.get(key, clazz);
        } catch (OperationException e) {
            boolean nullParam = clazz == null;
            LOGGER.error(String.format("Error while fetching %s data for key %s: ", nullParam ? null : clazz.getName(), nullParam ? null : key), e);
        }
        return null;
    }

    public  <V, T extends AerospikeRecord<V>> boolean  delete(Class<T> clazz, V key) {
        try {
            client.delete(clazz, key);
            return true;
        } catch (OperationException e) {
            boolean nullParam = clazz == null;
            LOGGER.error(String.format("Error while deleting %s data for key %s: ", nullParam ? null : clazz.getName(), nullParam ? null : key), e);
        }
        return false;
    }

    public <T extends AerospikeRecord> void put(T aerospikeData) {
        try {
            client.put(aerospikeData);
        } catch (OperationException e) {
            LOGGER.error(String.format("Unable to put %s in aerospike", aerospikeData == null ? null : aerospikeData.getClass().getName()));
        }
    }

    public <T extends AerospikeRecord> boolean putIfNotExists(T aerospikeData) {
        try {
            return client.putIfNotExists(aerospikeData);
        } catch (OperationException e) {
            LOGGER.error(String.format("Unable to put %s in Aerospike for key", aerospikeData == null ? null : aerospikeData.getClass().getName()));
        }
        return false;
    }


}
