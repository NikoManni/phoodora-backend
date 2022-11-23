package com.phoodora.restapi.models;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "restaurant_id")
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "address")
    public String address;

    @Column(name = "city")
    public String city;

    @Column(name = "postal_code")
    public String postal_code;

    @Column(name = "image")
    public String image;

    @Column(name = "operating_hours")
    public String operating_hours;

    @Column(name = "price_level")
    public String price_level;

    @Column(name = "type")
    public String type;

    @Column(name = "users_id")
    public int users_id;

    public Restaurant() {}

    public Restaurant(String name, String address, String city, String postal_code, String image, String operating_hours, String price_level, String type, int users_id) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.image = image;
        this.operating_hours = operating_hours;
        this.price_level = price_level;
        this.type = type;
        this.users_id = users_id;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return this.postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOperating_hours() {
        return this.operating_hours;
    }

    public void setOperating_hours(String operating_hours) {
        this.operating_hours = operating_hours;
    }

    public String getPrice_level() {
        return this.price_level;
    }

    public void setPrice_level(String price_level) {
        this.price_level = price_level;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUsers_id() {
        return this.users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
}
