package com.techelevator.dao;

import com.techelevator.model.Beer;

import java.util.List;

public interface BeerDao {
    //list all beers
    List<Beer> listAllBeers();

    // list beers by brewery
    List<Beer> listBeerByBrewer(int id);


    Beer getBeer(int id);

    //add a beer to a brewery
    Beer addBeer(Beer beer);

    // remove a beer
    void deleteBeer(int id);

    void updateBeer(Beer beer);
}
