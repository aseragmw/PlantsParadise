package com.example.plantsparadise.features.home.domain.models;

public class Plant {
    private String title;
    private String description;
    private String imgURL;
    private double price;

    public Plant(String title, String description, String imgURL, double price) {
        this.title = title;
        this.description = description;
        this.imgURL = imgURL;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", price=" + price +
                '}';
    }
}
