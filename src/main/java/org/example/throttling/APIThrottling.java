package org.example.throttling;

import java.net.HttpRetryException;

public interface APIThrottling {

    void consumeBucketToken(APIBucket defaultAPIBucket) throws HttpRetryException;
}
