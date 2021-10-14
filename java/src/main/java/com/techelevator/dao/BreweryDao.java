package com.techelevator.dao;

import com.techelevator.model.Brewery;

import java.util.List;

public interface BreweryDao {

    //first trello card, list all breweries

    List<Brewery> listAllBreweries();

    List<Brewery> listByPostalCode(String zip);

    List<Brewery> listByCity(String city);

    //get single brewery
    Brewery getBrewery(int id);
    // function to allow brewers to add a brewery
    Brewery addBrewery(Brewery brewery);

    //delete a brewery, only for admin
    void deleteBrewery(int id);

    void updateBrewery(Brewery brewery);
}
