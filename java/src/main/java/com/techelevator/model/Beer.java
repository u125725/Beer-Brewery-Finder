package com.techelevator.model;

public class Beer {

        private int id;
        private int breweryId;
        private String name;
        private int catId;
        private int styleId;
        private String abv;
        private String ibu;
        private String srm;
        private String description;
        private String category;
        private String image;

    public Beer() {
    }

    public Beer(int id, int breweryId, String name, int catId,
        int styleId, String abv, String ibu, String srm, String description) {
        this.id = id;
        this.breweryId = breweryId;
        this.name = name;
        this.catId = catId;
        this.styleId = styleId;
        this.abv = abv;
        this.ibu = ibu;
        this.srm = srm;
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public String getSrm() {
        return srm;
    }

    public void setSrm(String srm) {
        this.srm = srm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



