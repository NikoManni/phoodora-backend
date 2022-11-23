package com.phoodora.restapi.models;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.simple.JSONObject;

import java.sql.Timestamp;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Entity
@Table(name = "order", schema = "public")
@DynamicUpdate
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "order_id")
    public int id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    public JSONObject order_data;

    @Column(name = "time")
    private String time;

    @Column(name = "received")
    public boolean received;

    @Column(name = "preparing")
    public boolean preparing;

    @Column(name = "waiting")
    public boolean waiting;

    @Column(name = "delivering")
    public boolean delivering;

    @Column(name = "delivered")
    public boolean delivered;

    @Column(name = "eta")
    public Timestamp eta;

    @Column(name = "users_id")
    public int users_id;

    @Column(name = "restaurant_id")
    public int restaurant_id;

    public Order() {}

    public Order(JSONObject order_data, String time, int users_id, int restaurant_id) {
        this.order_data = order_data;
        this.time = time;
        this.users_id = users_id;
        this.restaurant_id = restaurant_id;

        this.received = false;
        this.preparing = false;
        this.waiting = false;
        this.delivering = false;
        this.delivered = false;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getOrder_data() {
        return this.order_data;
    }

    public void setOrder_data(JSONObject order_data) {
        this.order_data = order_data;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isReceived() {
        return this.received;
    }

    public boolean getReceived() {
        return this.received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public boolean isPreparing() {
        return this.preparing;
    }

    public boolean getPreparing() {
        return this.preparing;
    }

    public void setPreparing(boolean preparing) {
        this.preparing = preparing;
    }

    public boolean isWaiting() {
        return this.waiting;
    }

    public boolean getWaiting() {
        return this.waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public boolean isDelivering() {
        return this.delivering;
    }

    public boolean getDelivering() {
        return this.delivering;
    }

    public void setDelivering(boolean delivering) {
        this.delivering = delivering;
    }

    public boolean isDelivered() {
        return this.delivered;
    }

    public boolean getDelivered() {
        return this.delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public Timestamp getEta() {
        return this.eta;
    }

    public void setEta(Timestamp eta) {
        this.eta = eta;
    }

    public int getUsers_id() {
        return this.users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public int getRestaurant_id() {
        return this.restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

}
