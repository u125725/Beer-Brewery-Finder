begin transaction;

alter sequence breweries_id_seq restart with 2000;

alter sequence beers_id_seq restart with 7000;


commit transaction;
