package com.revature.models;

import java.util.Date;
import java.util.Objects;

public class AdditionalInformation {

    private String first_name;
    private String last_name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String username;
    private String password;
    private boolean active;
    private String application_status;
    private String email;
    private String access_levels;
    private int bank_balance;


    public AdditionalInformation() {
    }

    public AdditionalInformation(String first_name, String last_name, String street, String city, String state, String zip) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getBank_balance() {
        return bank_balance;
    }

    public void setBank_balance(int bank_balance) {
        this.bank_balance = bank_balance;
    }

    public String getApplication_status() {
        return application_status;
    }

    public void setApplication_status(String application_status) {
        this.application_status = application_status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess_levels() {
        return access_levels;
    }

    public void setAccess_levels(String access_levels) {
        this.access_levels = access_levels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdditionalInformation)) return false;
        AdditionalInformation that = (AdditionalInformation) o;
        return Objects.equals(getFirst_name(), that.getFirst_name()) && Objects.equals(getLast_name(), that.getLast_name()) && Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getState(), that.getState()) && Objects.equals(getZip(), that.getZip()) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst_name(), getLast_name(), getStreet(), getCity(), getState(), getZip());
    }

    @Override
    public String toString() {
        return "AdditionalInformation{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip +
                '}';
    }


}
