<template>
  <div class="page">
    <header id="beer">
      <div id="title">
        <h1>{{ beer.name }}</h1>
        <h4>From: <router-link v-bind:to="{name: 'breweryDetails', params: {breweryId: brewery.id}} ">{{ brewery.name }}</router-link></h4>
      </div>
      <div id="beerDetails">
        <div class="stats">
          <h3>{{ beer.category }}</h3>
          <h3>ABV: {{ beer.abv }}%</h3>
        </div>
        <p>{{ beer.description }}</p>
        <div id="beerPic">
          <img id="pic" v-bind:src="beer.image" alt="Beer Type Image" />
        </div>
      </div>
    </header>
    <div id="beerContent">
      <div class="reviewList">
        <header>
          <h1>Beer Reviews</h1>
        </header>
        <div class="review" v-if="beerReviews.length == 0">
          <h3>There Are Currently No Reviews</h3>
        </div>
        <div class="review" v-for="review in beerReviews" :key="review.id">
          <h3>{{ review.userName }}</h3>
          <p>{{ review.review }}</p>
          <div class="badRating red" v-if="review.rating === false">
            <h2>
              <i class="glyphicon glyphicon-thumbs-down"></i>
            </h2>
          </div>
          <div class="goodRating green" v-if="review.rating === true">
            <h2>
              <i class="glyphicon glyphicon-thumbs-up"></i>
            </h2>
          </div>
        </div>
      </div>
      <div id="overallRatingContainer">
        <div id="overallRating">
          <div id="good" class="goodRating">
            <h1>
              <i class="glyphicon glyphicon-thumbs-up"></i>
            </h1>
          </div>
          <div id="bad" class="badRating">
            <h1>
              <i class="glyphicon glyphicon-thumbs-down"></i>
            </h1>
          </div>
        </div>
        <h3 v-if="overallRating >= 0">{{ overallRating }}% of people like this beer</h3>
        <h3 v-if="overallRating == -1">There are currently no ratings</h3>
      </div>
    </div>
  </div>
</template>

<script>
import beerService from "../services/BeerService.js";
import breweryService from "../services/BreweryService.js";
export default {
  data() {
    return {
      beer: {
        id: -1,
      },
      brewery: {
        id: -1,
      },
      beerReviews: [],
      overallRating: -1,
    };
  },
  methods: {
    calculateRating(){
      if(this.beerReviews.length > 0){
        let ratingsAsNumbers = this.beerReviews.map((review) => {
        let num;
        if(review.rating){
          num = 1;
        }
        else{
          num = 0;
        }
        return num;
      });

      let sum = ratingsAsNumbers.reduce((total, num) => {
        return total + num;
      })
      this.overallRating = ((sum / ratingsAsNumbers.length) * 100).toFixed(0);
      }
      
    }
  },
  created() {
    this.beer.id = this.$route.params.beerId;
    this.brewery.id = this.$route.params.breweryId;
    beerService.getBeerDetails(this.beer.id).then((response) => {
      this.beer = response.data;
    });
    breweryService.getBreweryDetails(this.brewery.id).then((response) => {
      this.brewery = response.data;
    });
    beerService.listBeerReviews(this.beer.id).then((response) => {
      this.beerReviews = response.data;
      this.calculateRating();
      if(this.overallRating >= 50){
        document.getElementById("bad").classList.add("gray");
        document.getElementById("good").classList.add("green");
      }
      else if(this.overallRating > 0){
        document.getElementById("bad").classList.add("red");
        document.getElementById("good").classList.add("gray");
      }
      else{
        document.getElementById("bad").classList.add("gray");
        document.getElementById("good").classList.add("gray");
      }
    });
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");
img {
  height: 150px;
}

#beer {
  display: flex;
  align-items: center;
  min-height: 150px;
  background-color: rgb(46, 196, 181, 0.5);
  color: rgb(1, 22, 39);
}

h1 {
  font-size: 50px;
  font-family: "Montserrat", sans-serif;
  padding: 5px;
  margin: 25px;
}

p {
  font-family: "Montserrat", sans-serif;
}

#title {
  text-align: center;
}

#beerDetails {
  display: flex;
  flex-grow: 1;
  justify-content: space-evenly;
  align-items: center;
}

.reviewList {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 60px;
  flex-grow: 3;
}

.review {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border: 3px rgb(1, 22, 39) solid;
  border-radius: 6px;
  min-height: 80px;
  width: 70%;
  min-width: 700px;
  padding: 1rem;
  margin: 10px;
  background-color: rgb(255, 159, 28, 0.7);
  color: rgb(1, 22, 39);
}

.badRating,
.goodRating {
  border: 3px black solid;
  border-radius: 6px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
}

.red {
  background-color: rgb(231, 29, 54);
}

.green {
  background-color: green;
}

.gray {
  background-color: rgb(211, 210, 210);
}

#beerContent {
  display: flex;
}

#overallRating {
  display: flex;
  justify-content: center;
  margin-top: 200px;
  margin-right: 40px;
  text-align: center;
  flex-grow: 1;
}

#overallRating > div {
  padding: 60px;
  margin-right: 60px;
  color: black;
  border: 4px black solid;
}
</style>