package ru.markelov.security.FirstSecurityApp.DTO;

public class ClientDTO {
    public Long phoneNumberOne;
    public Long phoneNumberTwo;
    public String massage;

    public Long getPhoneNumberOne() {
        return phoneNumberOne;
    }

    public void setPhoneNumberOne(Long phoneNumberOne) {
        this.phoneNumberOne = phoneNumberOne;
    }

    public Long getPhoneNumberTwo() {
        return phoneNumberTwo;
    }

    public void setPhoneNumberTwo(Long phoneNumberTwo) {
        this.phoneNumberTwo = phoneNumberTwo;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
