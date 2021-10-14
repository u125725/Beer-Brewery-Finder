drop table if exists breweries cascade;
drop table if exists categories cascade;
drop table if exists brewery_geocode cascade;
drop table if exists styles cascade;
drop table if exists beers cascade;
drop table if exists reviews cascade;

begin transaction;


create table breweries

(
        id serial,
        name varchar (200),
        address1 varchar (200),
        address2 varchar (200),
        city varchar (200),
        state varchar (200),
        code varchar (200),
        country varchar (200),
        phone varchar (200),
        website text,
        filepath varchar (200),
        descript text,
        last_mod varchar (200),
        latitude text,
        longitude text,
        user_id int,
        

        
        constraint pk_brewery_id primary key (id),
        --constraint fk_brewed_at foreign key (id) references beers (brewery_id)
        constraint fk_user_id foreign key (user_id) references users (user_id)     
);



create table categories
(
        id int,
        cat_name varchar(200),
        image varchar(200),
        
        constraint pk_cat_id primary key (id)
        --constraint fk_cat_beer_id foreign key (id) references beers (cat_id)
);


create table beers
(
        id serial,
        brewery_id int,
        name varchar(200),
        cat_id int,
        style_id int,
        abv varchar(64),
        ibu varchar(64),
        srm varchar(64),
        upc varchar(64),
        filepath varchar(200),
        descript text,
        last_mod varchar(200),
        
        constraint pk_id primary key (id),
        constraint fk_brewery_id foreign key (brewery_id) references breweries (id),
        constraint fk_cat_id foreign key (cat_id) references categories (id)
        
               
);
create table reviews
(
        id serial,
        user_id int,
        beer_id int,
        review text,
        rating boolean,
        
        constraint pk_reviews_id primary key (id),
        constraint fk_user_review foreign key (user_id) references users (user_id),
        constraint fk_beer_review foreign key (beer_id) references beers (id)
);

commit transaction;


