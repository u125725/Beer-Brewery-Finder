package com.techelevator.controller;

import com.techelevator.dao.ReviewsDao;
import com.techelevator.model.Beer;
import com.techelevator.model.Reviews;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReviewsController {
    private ReviewsDao reviewsDao;

    public ReviewsController(ReviewsDao reviewsDao){
        this.reviewsDao = reviewsDao;
    }

    @PostMapping("/beers/{id}")
    public void addReview(@RequestBody Reviews reviews){
        reviewsDao.addReview(reviews);
    }

    @GetMapping("/beers/{id}/reviews")
    public List<Reviews> getBeerReview(@PathVariable int id){
        return reviewsDao.getReviewList(id);
    }




}
