package com.techelevator.dao;

import com.techelevator.model.Brewery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.rmi.activation.ActivationGroup_Stub;
import java.util.ArrayList;
import java.util.List;

@Component //might need to change this to @Service
public class JdbcBreweryDao implements BreweryDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcBreweryDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Brewery> listAllBreweries() {
        List<Brewery> breweryList = new ArrayList<>();
        String sql = "select * " + // using select * for now for testing
                "from breweries";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            /*Since there is so much data for a brewery, I will start with a maptorowset for just the list*/
            breweryList.add(mapListToRow(results));
        }
        return breweryList;
    }

    @Override
    public List<Brewery> listByPostalCode(String zip) {

        List<Brewery> breweryList = new ArrayList<>();
        String sql = "select * " +
                "from breweries where code = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, zip);
        while(results.next()){
            breweryList.add(mapListToRow(results));
        }
        return breweryList;
    }

    @Override
    public List<Brewery> listByCity(String city) {
        List<Brewery> breweryList = new ArrayList<>();
        String sql = "select * " +
                "from breweries where city = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, city);
        while(results.next()){
            breweryList.add(mapListToRow(results));
        }
        return breweryList;
    }

    @Override
    public Brewery getBrewery(int id) {
        Brewery brewery = null;
        String sql = "select * from breweries where id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if(results.next()){
            brewery = mapListToRow(results);
        }
        return brewery;
    }

    @Override
    public Brewery addBrewery(Brewery brewery) {
        String sql = "insert into breweries(name, address1, city, state, code, country, website, phone, descript, latitude, longitude, user_id) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "returning id";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, brewery.getName(), brewery.getStreet(),
                brewery.getCity(), brewery.getState(), brewery.getPostalCode(), brewery.getCountry(),
                brewery.getWebsiteUrl(), brewery.getPhone(), brewery.getDescription(), brewery.getLatitude(),
                brewery.getLongitude(), brewery.getUserId());
        return getBrewery(newId);

    }

    @Override
    public void deleteBrewery(int id) {
        String sql = "delete from breweries where id = ?";
        jdbcTemplate.update(sql, id);
    }

    // update brewery
    @Override
    public void updateBrewery(Brewery brewery) {
        String sql = "update breweries set name = ?, address1 = ?, code = ?, phone = ?, website = ?, descript = ?, latitude = ?, longitude = ? " +
                "where id = ?";
        System.out.println(brewery);
        jdbcTemplate.update(sql, brewery.getName(), brewery.getStreet(), brewery.getPostalCode(), brewery.getPhone(), brewery.getWebsiteUrl(),
                brewery.getDescription(), brewery.getLatitude(), brewery.getLongitude(), brewery.getId());
    }





    /*Mapping for brewery list*/
    private Brewery mapListToRow(SqlRowSet results) {
        Brewery brewery = new Brewery();
        brewery.setId(results.getInt("id"));
        brewery.setName(results.getString("name"));
        brewery.setStreet(results.getString("address1"));
        brewery.setCity(results.getString("city"));
        brewery.setState(results.getString("state"));
        brewery.setPostalCode(results.getString("code"));
        brewery.setPhone(results.getString("phone"));
        brewery.setWebsiteUrl(results.getString("website"));
        brewery.setDescription(results.getString("descript"));
        brewery.setLastModified(results.getString("last_mod"));
        brewery.setLatitude(results.getString("latitude"));
        brewery.setLongitude(results.getString("longitude"));
        brewery.setUserId(results.getInt("user_id"));

        return brewery;

    }
}
