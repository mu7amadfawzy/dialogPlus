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

    public int getPhone_code() {
        return phone_code;
    }

    public String getPhoneCodeStr() {
        return phone_code + "";
    }
    public void setPhone_code(int phone_code) {
        this.phone_code = phone_code;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }
}
