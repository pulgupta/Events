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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Just another POJO whose handleMessage will be called as per the data and the
 * contentType of the message which we are receiving on the queue.
 */
@Component
public class ProcessMessageAnother {

    private static final Logger logger = LoggerFactory.getLogger(ProcessMessageAnother.class);

    public void handleMessage(Message message)
    {
        logger.info("Received Message from another " + message);
    }

    public void handleMessage(byte[] message)
    {
        logger.info("Received Message from another as byte array " + message);
        ByteArrayInputStream in = new ByteArrayInputStream(message);
        try {
            ObjectInputStream is = new ObjectInputStream(in);
            Message messageConverted = (Message) is.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            logger.error("Not able to convert message");
            e.printStackTrace();
        }

    }

    public void handleMessage(Order message)
    {
        logger.info("Received Message from another as Order " + message);
    }
}
