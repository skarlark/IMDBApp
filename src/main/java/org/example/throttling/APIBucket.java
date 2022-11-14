package org.example.throttling;

import java.net.HttpRetryException;

public interface APIBucket {

    void consumeBucket() throws HttpRetryException;
}
