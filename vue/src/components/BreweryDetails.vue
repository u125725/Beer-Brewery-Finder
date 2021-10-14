<template>
  <div>
    <div id="breweryDetails">
      <header>
        <h1 id="breweryName">{{ brewery.name }}</h1>
      </header>
      <div id="info">
        <div id="address">
          <h3 class="street">{{ brewery.street }}</h3>
          <h3 class="city state">{{ brewery.city }}, {{ brewery.state }}</h3>
        </div>
        <div id="contactInfo">
          <h3 class="phone">Phone: {{ brewery.phone }}</h3>
          <h3 class="website">Website: {{ brewery.websiteUrl }}</h3>
        </div>
        <p class="description">{{ brewery.description }}</p>
      </div>
    </div>
    <div id="breweryContent">
      <div class="beerList">
        <header>
          <h1>Beers On Tap</h1>
        </header>
        <div class="spacing">
          <router-link
            v-if="this.$store.state.user.id === brewery.userId"
            id="addBeer"
            tag="button"
            v-bind:to="{ name: 'addBeer' }"
            >Add A Beer</router-link
          >
        </div>
        <div class="beer" v-for="beer in beers" :key="beer.id">
          <router-link
            v-bind:to="{
              name: 'beerDetails',
              params: { breweryId: brewery.id, beerId: beer.id },
            }"
          >
            <h3 class="name">{{ beer.name }}</h3>
          </router-link>
          <div class="beerActions">
            <router-link
              tag="button"
              v-if="currentUser !== brewery.userId"
              v-bind:to="{ name: 'addReview', params: { beerId: beer.id } }"
              >Review</router-link
            >
            <router-link
              tag="button"
              v-if="currentUser === brewery.userId"
              v-bind:to="{ name: 'updateBeer', params: { beerId: beer.id } }"
              >Update</router-link
            >
            <button
              v-if="currentUser === brewery.userId"
              v-on:click="deleteBeer(beer.id)"
            >
              Delete
            </button>
          </div>
        </div>
      </div>
      <!--<div class="reviewList">
        <header>
          <h1>Brewery Reviews</h1>
        </header>
        <div class="spacing">
          <router-link
            tag="button"
            v-if="currentUser !== brewery.userId"
            v-bind:to="{ name: 'addBeer' }"
            >Add A Review</router-link
          >
        </div>
        <div class="review" v-if="breweryReviews.length == 0">
          <h3>There Are Currently No Reviews</h3>
        </div>
      </div>-->
    </div>
  </div>
</template>

<script>
import breweryService from "../services/BreweryService.js";
import beerService from "../services/BeerService.js";
export default {
  data() {
    return {
      brewery: {
        id: -1,
      },
      beers: [],
      breweryReviews: [],
      newReview: "",
      currentUser: this.$store.state.user.id,
    };
  },
  created() {
    this.brewery.id = this.$route.params.breweryId;
    breweryService.getBreweryDetails(this.brewery.id).then((response) => {
      this.brewery = response.data;
    });
    beerService.listBreweryBeers(this.brewery.id).then((response) => {
      this.beers = response.data;
    });
  },
  methods: {
    deleteBeer(id) {
      let choice = confirm("Are you sure you want to delete your account?");
      if (choice){
        beerService
        .deleteBeer(id)
        .then((response) => {
          if (response.status === 200 || response.status === 204) {
            document.location.reload();
          }
        })
        .catch((error) => console.log(error));
      }
      
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");
.beerList,
.reviewList {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 60px;
}

.beer,
.review {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border: 3px rgb(1, 22, 39) solid;
  border-radius: 6px;
  min-height: 80px;
  width: 60%;
  min-width: 700px;
  padding: 1rem;
  margin: 10px;
}

.name {
  color: rgb(1, 22, 39);
  font-family: "Montserrat", sans-serif;
}

h1 {
  font-size: 50px;
  font-family: "Montserrat", sans-serif;
  padding: 5px;
  margin: 25px;
}

button {
  font-size: 20px;
}

.beerActions > * {
  margin: 5px;
}

#breweryContent {
  display: flex;
}

#breweryContent > div {
  flex-grow: 1;
}

#breweryDetails {
  display: flex;
  align-items: center;
  min-height: 150px;
  background-color: rgb(46, 196, 181, 0.5);
  color: rgb(1, 22, 39);
}

.beer {
  background-color: rgb(231, 29, 54, 0.7);
}

.review {
  background-color: rgb(255, 159, 28, 0.7);
  color: rgb(1, 22, 39);
}

#info {
  display: flex;
  flex-grow: 1;
  justify-content: space-evenly;
}

p {
  font-family: "Montserrat", sans-serif;
}

.spacing {
  min-height: 40px;
}
</style>