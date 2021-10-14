package com.techelevator.controller;


import com.techelevator.dao.BreweryDao;
import com.techelevator.model.Brewery;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.BreakIterator;
import java.util.List;

@RestController
@CrossOrigin
public class BreweryController {

    private BreweryDao breweryDao;

    public BreweryController(BreweryDao breweryDao) {
        this.breweryDao = breweryDao;
    }

    //Add a brewery to the list
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_BREWER')")
    @PostMapping("/addBrewery")
    public void addBrewery(@RequestBody Brewery brewery) {
        breweryDao.addBrewery(brewery);
    }

    //list all breweries by a specific zipcode
    @GetMapping("/searchByZip/{zip}")
    public List<Brewery> searchByZip(@PathVariable String zip) {
        return breweryDao.listByPostalCode(zip);
    }

    //list all breweries by a specific city

    @GetMapping("/searchByCity/{city}")
    public List<Brewery> searchByCity(@PathVariable String city){
        return breweryDao.listByCity(city);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteBrewery/{id}")
    public void deleteBrewery(@PathVariable int id){
        breweryDao.deleteBrewery(id);
    }

    @GetMapping("/breweries/{id}")
    public Brewery getBrewery(@PathVariable int id){
        return breweryDao.getBrewery(id);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_BREWER')")
    @PutMapping("/updateBrewery/{id}")
    public void updateBrewery(@RequestBody Brewery brewery){
        breweryDao.updateBrewery(brewery);
    }
}
