package com.klgs.rmq.consumer;

/**
 * Created by pulgupta on 08/10/16.
 */


import com.klgs.rmq.configuration.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProcessMessageRube {

    private static final Logger logger = LoggerFactory.getLogger(ProcessMessageRube.class);

    public void handleMessage(Message message)
    {
        logger.info("Received Message from rube " + message);
    }
    public void handleMessage(byte[] message)
    {
        logger.info("Received Message from rube as byte array " + message);
    }
    public void handleMessage(Order message)
    {
        logger.info("Received Message from rube as order " + message);
    }
}
