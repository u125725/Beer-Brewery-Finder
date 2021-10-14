package com.techelevator.model;

public class Reviews {
    private int id;
    private int userId;
    private int beerId;
    private String review;
    private boolean rating;
    private String userName;

    public Reviews() {
    }

    public Reviews(int id, int userId, int beerId, String review, boolean rating) {
        this.id = id;
        this.userId = userId;
        this.beerId = beerId;
        this.review = review;
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean getRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }
}
