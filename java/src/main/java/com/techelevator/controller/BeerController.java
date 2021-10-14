package com.techelevator.controller;

import com.techelevator.dao.BeerDao;
import com.techelevator.model.Beer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BeerController {

    private BeerDao beerDao;

    public BeerController(BeerDao beerDao){
        this.beerDao = beerDao;
    }

    //search beer by brewery id;
    @GetMapping("/breweries/{id}/beers")
    public List<Beer> searchByBreweryId(@PathVariable int id){
        return beerDao.listBeerByBrewer(id);
    }
    // get single beer by id
    @GetMapping("/beers/{id}")
    public Beer getBeerById(@PathVariable int id){
        return beerDao.getBeer(id);
    }

    @PostMapping("/breweries/{id}/addBeer")
    public Beer addBeer(@RequestBody Beer beer){
        return beerDao.addBeer(beer);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_BREWER')")
    @DeleteMapping("/deleteBeer/{id}")
    public void deleteBeer(@PathVariable int id){
        beerDao.deleteBeer(id);
    }
    @GetMapping("/allBeers")
    public List<Beer> getBeers(){
        return beerDao.listAllBeers();
    }
    @PreAuthorize("hasRole('ROLE_BREWER')")
    @PutMapping("/updateBeer/{id}")
    public void updateBeer(@RequestBody Beer beer){
        beerDao.updateBeer(beer);
    }



}
