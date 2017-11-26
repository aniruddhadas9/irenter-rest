package com.candifood.rest.entities;

public class Restaurant {
    private String name;
    private String address;
    private String email;
    private String phone;

    public Restaurant(String name, String address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }
    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
