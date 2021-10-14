package com.techelevator.dao;

import com.techelevator.model.Beer;
import com.techelevator.model.Reviews;

import java.util.List;

public interface ReviewsDao {
    Reviews addReview(Reviews reviews);

    Reviews getReviews(int id);

    List<Reviews> getReviewList(int id);
}
