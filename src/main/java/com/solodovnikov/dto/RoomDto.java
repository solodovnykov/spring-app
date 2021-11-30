package com.solodovnikov.dto;

public class RoomDto {
    private Integer id;

    private Integer maxPersons;

    private Integer pricePerNight;

    private Integer status;

    public RoomDto() {

    }

    public RoomDto(Integer id, Integer maxPersons, Integer pricePerNight, Integer status) {
        this.id = id;
        this.maxPersons = maxPersons;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxPersons() {
        return maxPersons;
    }

    public void setMaxPersons(Integer maxPersons) {
        this.maxPersons = maxPersons;
    }

    public Integer getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Integer pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
