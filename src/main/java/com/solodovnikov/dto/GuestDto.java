package com.solodovnikov.dto;

public class GuestDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private Integer stateOfAccount;

    public GuestDto() {

    }

    public GuestDto(Integer id, String firstName, String lastName, Integer stateOfAccount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stateOfAccount = stateOfAccount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getStateOfAccount() {
        return stateOfAccount;
    }

    public void setStateOfAccount(Integer stateOfAccount) {
        this.stateOfAccount = stateOfAccount;
    }
}
