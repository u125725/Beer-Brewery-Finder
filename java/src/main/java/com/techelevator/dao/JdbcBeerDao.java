package com.techelevator.dao;

import com.techelevator.model.Beer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcBeerDao implements BeerDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcBeerDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Beer> listAllBeers() {
        List<Beer> beerList = new ArrayList<>();
        String sql = "select *, categories.cat_name, categories.image from beers " +
                "join categories on categories.id = beers.cat_id ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()){
            beerList.add(mapBeerToRow(result));
        }
        return beerList;
    }

    @Override
    public List<Beer> listBeerByBrewer(int id) {
        List<Beer> beerByBrewery = new ArrayList<>();
        String sql = "select *, categories.cat_name, categories.image from beers " +
                "join categories on categories.id = beers.cat_id " +
                "where brewery_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while(results.next()){
            beerByBrewery.add(mapBeerToRow(results));
        }
        return beerByBrewery;
    }

    @Override
    public Beer getBeer(int id) {
        Beer beer = new Beer();
        String sql = "select * " +
                "from beers " +
                "join categories on categories.id = beers.cat_id " +
                "where beers.id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            beer = mapBeerToRow(results);
        }
        return beer;
    }

    @Override
    public Beer addBeer(Beer beer) {
        String sql = "insert into beers (name, brewery_id, cat_id, style_id, abv, ibu, " +
                "descript) " +
                "values(?,?,?,?,?,?,?) returning id";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, beer.getName(), beer.getBreweryId(),
                beer.getCatId(), beer.getStyleId(), beer.getAbv(), beer.getIbu(), beer.getDescription());

        return getBeer(newId);
    }

    @Override
    public void deleteBeer(int id) {
        String sql = "delete from beers where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateBeer(Beer beer) {
        String sql = "update beers " +
                "set name = ?, cat_id = ?, abv = ?, ibu = ?, descript = ? " +
                "where id = ?";
        jdbcTemplate.update(sql, beer.getName(), beer.getCatId(), beer.getAbv(),
                beer.getIbu(), beer.getDescription(), beer.getId());
    }


        // todo might need to add a different map to row
    private Beer mapBeerToRow(SqlRowSet results) {
        Beer beer = new Beer();
        beer.setId(results.getInt("id"));
        beer.setBreweryId(results.getInt("brewery_id"));
        beer.setName(results.getString("name"));
        beer.setCatId(results.getInt("cat_id"));
        beer.setStyleId(results.getInt("style_id"));
        beer.setAbv(results.getString("abv"));
        beer.setIbu(results.getString("ibu"));
        beer.setDescription(results.getString("descript"));
        beer.setCategory(results.getString("cat_name"));
        beer.setImage(results.getString("image"));
        return beer;
    }
}
