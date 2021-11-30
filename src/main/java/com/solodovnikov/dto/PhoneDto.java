package com.solodovnikov.dto;

public class PhoneDto {
    private Integer id;

    private String number;

    public PhoneDto() {

    }

    public PhoneDto(Integer id, String number) {
        this.id = id;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
