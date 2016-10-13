package com.klgs.rmq.producer;

import com.klgs.rmq.configuration.RabbitMq;
import com.rabbitmq.client.MessageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pulgupta on 02/10/16.
 */

/**
 * A message producer. Nothing other than that
 */
@RestController
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    @Autowired
    RabbitTemplate tr;

	@RequestMapping("/")
	public String sendMessage(@RequestParam("message") String message, @RequestParam("queue") String queue) {
        logger.info("See if template is there " + tr.getExchange());

        if(queue.equals("rube")) {
            tr.convertAndSend("rube-key", this.getMessage("{\n" +
                    "\t\"orderid\": 12,\n" +
                    "\t\"itemDescription\": \"Car\"\n" +
                    "}"));
        }
        else if (queue.equals("another")) {
            tr.convertAndSend("another-key", this.getMessage("{\n" +
                    "\t\"orderid\": 12,\n" +
                    "\t\"itemDescription\": \"Bike\"\n" +
                    "}"));
        }
        else {
            tr.convertAndSend("latest-key", this.getMessage("{\n" +
                    "\t\"orderid\": 14,\n" +
                    "\t\"itemDescription\": \"Bike\"\n" +
                    "}"));
        }
        return "success";
    }

    private Message getMessage(String messageText) {
        Message message = MessageBuilder.withBody(messageText.getBytes())
                .setMessageId("123")
                .setContentType("application/json")
                .setHeader("bar", "baz")
                .build();
        return message;
    }
}
