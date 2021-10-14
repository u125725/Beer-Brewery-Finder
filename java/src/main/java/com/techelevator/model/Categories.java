package com.techelevator.model;

public class Categories {

    private int id;
    private String catName;
    private String image;


    public Categories() {
    }

    public Categories(int id, String catName, String image) {
        this.id = id;
        this.catName = catName;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
