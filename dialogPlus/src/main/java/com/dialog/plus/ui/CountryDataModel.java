package com.dialog.plus.ui;

/**
 * Created by fawzy on 04,September,2019
 * ma7madfawzy@gmail.com
 **/
public class CountryDataModel {
    private String name, code, english_name;
    private int phone_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPhoneCode() {
        return phone_code;
    }

    public String getPhoneCodeStr() {
        return phone_code + "";
    }

    public void setPhoneCode(int phone_code) {
        this.phone_code = phone_code;
    }

    public String getEnglishName() {
        return english_name;
    }

    public void setEnglishName(String english_name) {
        this.english_name = english_name;
    }
}
