package com.auto.qe.ui.model;

import java.util.List;

public class CarListingView {

    private List<CarListing> carListings;
    private int size;
    private CarListing firstListing;
    private CarListing lastListing;

    public CarListingView(List<CarListing> carListings) {
        this.carListings = carListings;
        this.size = carListings.size();
        this.firstListing = carListings.get(0);
        this.lastListing = carListings.get(this.size - 1);
    }

    public void checkStartingPriceOrder(CarListing lastFromPreviousPage) {
        if (lastFromPreviousPage.getPrice() < firstListing.getPrice()) {
            System.out.println("CAR LISTING NOT ORDERED PROPERLY BETWEEN PAGE TRANSITION");}
        else   System.out.println("CAR LISTING  ORDERED PROPERLY BETWEEN PAGE TRANSITION");


    }

    public void checkPriceSorting() {
        for (int i = 1; i < size; i++) {
            CarListing currentCarListing = carListings.get(i);
            CarListing previousCarListing = carListings.get(i - 1);

            // If current car listing is greater that previous car listing.
            // meaning:
            // if 5th ROW of the Car Listing Table has a price that is
            // greater than 4th ROW of the Car Listing,
            // it not properly ordered in descending order.
            if (currentCarListing.getPrice() > previousCarListing.getPrice()) {
                System.out.println("CAR LISTING NOT SHOWN IN DESCEDNING ORDER");
            }
            else   System.out.println("CAR LISTING  SHOWN IN DESCEDNING ORDER");
        }

    }

    public List<CarListing> getCarListings() {
        return carListings;
    }

    public void setCarListings(List<CarListing> carListings) {
        this.carListings = carListings;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public CarListing getFirstListing() {
        return firstListing;
    }

    public void setFirstListing(CarListing firstListing) {
        this.firstListing = firstListing;
    }

    public CarListing getLastListing() {
        return lastListing;
    }

    public void setLastListing(CarListing lastListing) {
        this.lastListing = lastListing;
    }
}
