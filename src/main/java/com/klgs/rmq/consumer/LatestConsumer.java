package com.klgs.rmq.consumer;

import com.klgs.rmq.configuration.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * Created by pulgupta on 11/10/16.
 */

/**
 * This is the new way of listening to messages.
 * If we see this is so close to the ways we deal with the REST interfaces
 * In this way we can easily get the message body as well as the headers
 */
@Component
public class LatestConsumer {

    private static final Logger logger = LoggerFactory.getLogger(LatestConsumer.class);

    @RabbitListener(queues = "rmq-latest-queue")
    public void listen(Order order, @Header("bar") String header, Message message) {
        logger.info("Got Data " + order);
        logger.info("Got Headers " + header);
        logger.info("Message id is " + message.getMessageProperties().getMessageId());
    }
}
