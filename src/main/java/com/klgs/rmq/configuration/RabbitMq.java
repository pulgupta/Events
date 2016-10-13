package com.klgs.rmq.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pulgupta on 02/10/16.
 */

/**
 * This class is the main configuration class.
 * The artifacts defined in this class are as follows
 *  exchanges
 *  queues
 *  bindings
 *  rabbitTemplate
 *  messageConverters
 *  amqpAdmin
 */
@Configuration
public class RabbitMq {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMq.class);

    @Qualifier("producer")
    @Autowired
    private ConnectionFactory rabbitConnectionFactory;

    @Bean
    DirectExchange rubeExchange() {
        return new DirectExchange("rmq-exchange", true, false);
    }

    @Bean
    public Queue rubeQueue() {
        return new Queue("rmq-rube-queue", true);
    }

    @Bean
    public Queue anotherQueue() {
        return new Queue("rmq-another-queue", true);
    }

    @Bean
    public Queue latestQueue() {
        return new Queue("rmq-latest-queue", true);
    }

    @Bean
    Binding rubeExchangeBinding(DirectExchange rubeExchange, Queue rubeQueue) {
        return BindingBuilder.bind(rubeQueue).to(rubeExchange).with("rube-key");
    }

    @Bean
    Binding anotherExchangeBinding(DirectExchange rubeExchange, Queue anotherQueue) {
        return BindingBuilder.bind(anotherQueue).to(rubeExchange).with("another-key");
    }

    @Bean
    Binding latestExchangeBinding(DirectExchange rubeExchange, Queue latestQueue) {
        return BindingBuilder.bind(latestQueue).to(rubeExchange).with("latest-key");
    }

    @Bean
    public AmqpAdmin amqpAdmin()
    {
        return new RabbitAdmin(rabbitConnectionFactory);
    }

    @Bean
    public RabbitTemplate rubeExchangeTemplate() {
        logger.info("Lets test autowiring " + rabbitConnectionFactory.getHost());
        RabbitTemplate r = new RabbitTemplate(this.rabbitConnectionFactory);
        r.setExchange("rmq-exchange");
        r.setMessageConverter(jsonMessageConverter());
        return r;
    }

    @Bean
    public MessageConverter jsonMessageConverter()
    {
        final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper());
        return converter;
    }

    @Bean
    public DefaultClassMapper classMapper()
    {
        DefaultClassMapper typeMapper = new DefaultClassMapper();
        typeMapper.setDefaultType(Order.class);
        return typeMapper;
    }
}
