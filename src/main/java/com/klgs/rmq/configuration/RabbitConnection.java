package com.klgs.rmq.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Created by pulgupta on 02/10/16.
 */

/**
 * This class contains the details required for creating a connection with the
 * rabbit server. The class serves not other purpose other than to connect to the
 * rabbit broker.
 */
@EnableRabbit
@Configuration
@PropertySources({@PropertySource("classpath:rabbitMq.properties")})
public class RabbitConnection {

    private static final Logger logger = LoggerFactory.getLogger(RabbitConnection.class);

    @Value("${rabbitmq.host}")
    private String host;

    @Value("${rabbitmq.port:5672}")
    private int port;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Qualifier("producer")
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cf = new CachingConnectionFactory(host, port);
        logger.info("Properties are " + host + " " + port);
        cf.setUsername(username);
        cf.setPassword(password);
        cf.setChannelCacheSize(50);
        logger.info("So the connection is created");
        return cf;
    }
}
