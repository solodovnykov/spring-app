package com.solodovnikov.dto;


public class CountryDto {

    private Integer id;

    private String name;

    public CountryDto() {

    }

    public CountryDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String country) {
        this.name = name;
    }

}
