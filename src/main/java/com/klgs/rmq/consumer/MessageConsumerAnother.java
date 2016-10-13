package com.klgs.rmq.consumer;

import com.klgs.rmq.configuration.RabbitMq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by pulgupta on 05/10/16.
 */

/**
 * This is just another listener class
 * In case you have already seen the LatestConsumer class then we might not need this class anymore
 * However this class may be necessary in case we want to override the default semantics as applied in
 * the latestConsumer
 */
@Component
public class MessageConsumerAnother {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumerAnother.class);

    @Autowired
    ConnectionFactory cf;

    @Autowired
    ProcessMessageAnother pm;

    @Autowired
    MessageConverter converter;

    @Bean(value = "another")
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(cf);
        container.setQueueNames("rmq-another-queue");
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        MessageListenerAdapter listener =  new MessageListenerAdapter(pm , converter);
        return listener;
    }
}
