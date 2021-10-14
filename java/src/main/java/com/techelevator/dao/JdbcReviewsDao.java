package com.techelevator.dao;

import com.techelevator.model.Reviews;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcReviewsDao implements ReviewsDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewsDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Reviews addReview(Reviews reviews) {
        String sql = "insert into reviews (user_id, beer_id, review, rating) " +
                "values(?, ?, ?, ?) returning id";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, reviews.getUserId(),
                reviews.getBeerId(), reviews.getReview(), reviews.getRating());
        return getReviews(newId);
    }

    @Override
    public Reviews getReviews(int id) {
        Reviews reviews = new Reviews();
        String sql = "select * from reviews where id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            reviews = mapToReview(results);
        }
        return reviews;
    }

    @Override
    public List<Reviews> getReviewList(int id) {
        List<Reviews> reviewsList = new ArrayList<>();
        String sql = "select *, users.username " +
                "from reviews " +
                "join users on users.user_id = reviews.user_id " +
                "where beer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            reviewsList.add(mapToReviewList(results));
        }
        return reviewsList;
    }

    private Reviews mapToReviewList(SqlRowSet results) {
        Reviews reviews = new Reviews();
        reviews.setId(results.getInt("id"));
        reviews.setUserId(results.getInt("user_id"));
        reviews.setBeerId(results.getInt("beer_id"));
        reviews.setReview(results.getString("review"));
        reviews.setRating(results.getBoolean("rating"));
        reviews.setUserName(results.getString("username"));
        return reviews;
    }

    private Reviews mapToReview(SqlRowSet results) {
        Reviews reviews = new Reviews();
        reviews.setId(results.getInt("id"));
        reviews.setUserId(results.getInt("user_id"));
        reviews.setBeerId(results.getInt("beer_id"));
        reviews.setReview(results.getString("review"));
        reviews.setRating(results.getBoolean("rating"));
        return reviews;
    }
}
