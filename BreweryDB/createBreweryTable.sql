drop table if exists breweries;
begin transaction;
create table breweries
(
        id serial,
        obdb_id varchar (200),
        name varchar(200),
        brewery_type varchar(64),
        street varchar(100),
        address_2 varchar(64),
        address_3 varchar(64),
        city varchar(64),
        state varchar(64),
        county_province varchar(64),
        postal_code varchar(10),
        website_url text,
        phone varchar(15),
        created_at date,
        updated_at date,
        country varchar(64),
        longitude numeric,
        latitude numeric,
        tags varchar(10),
        
        constraint pk_id primary key (id)
);


commit transaction;
        