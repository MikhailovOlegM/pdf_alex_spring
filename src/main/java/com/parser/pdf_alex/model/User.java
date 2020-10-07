package com.parser.pdf_alex.model;

import com.google.gson.JsonObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.lang.NonNull;

import java.sql.Date;

public class User {
    @Id
    @NonNull
    private Integer id;

    @Column
    private String city_lab;

    @Column
    @NonNull
    private String user_name;

    @Column
    @NonNull
    private Date birthday;

    @Column
    private String barcode;

    @Column
    private Date blood_analize_date;

    @Column
    private Date test_date;

    @Column
    private Date released_date;

    @Column
    private JsonObject short_data_container;

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public String getCity_lab() {
        return city_lab;
    }

    public void setCity_lab(String city_lab) {
        this.city_lab = city_lab;
    }

    @NonNull
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(@NonNull String user_name) {
        this.user_name = user_name;
    }

    @NonNull
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(@NonNull Date birthday) {
        this.birthday = birthday;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getBlood_analize_date() {
        return blood_analize_date;
    }

    public void setBlood_analize_date(Date blood_analize_date) {
        this.blood_analize_date = blood_analize_date;
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
    }

    public Date getReleased_date() {
        return released_date;
    }

    public void setReleased_date(Date released_date) {
        this.released_date = released_date;
    }

    public JsonObject getShort_data_container() {
        return short_data_container;
    }

    public void setShort_data_container(JsonObject short_data_container) {
        this.short_data_container = short_data_container;
    }
}
