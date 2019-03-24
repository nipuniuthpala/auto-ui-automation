package com.auto.qe.ui.model;

public class CarListing {

    private int registrationYear;
    private double price;

    public CarListing(int registrationYear, double price) {
        this.registrationYear = registrationYear;
        this.price = price;
    }

    public void validateYear2015() {
        if(registrationYear >= 2015 )
            System.out.println("Registration Year is after 2015");
        else  System.out.println("Registration Year is before 2015");
    }

    public int getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(int registrationYear) {
        this.registrationYear = registrationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
