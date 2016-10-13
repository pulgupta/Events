package com.klgs.rmq.configuration;

import org.springframework.amqp.core.Message;

/**
 * Created by pulgupta on 09/10/16.
 */

/**
 * This is a simple pojo. This will be passed as a message body.
 * We will embbed this in the spring provided message
 * Anything with this class is not specific to AMQP
 */
public class Order {

    private int orderid;
    private String itemDescription;

    public Order(){
        super();
    }

    public Order(int orderid, String itemDescription) {
        this.orderid=orderid;
        this.itemDescription=itemDescription;
    }

    public int getOrderid() {
        return orderid;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
