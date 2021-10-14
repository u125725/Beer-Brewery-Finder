<template>
  <!-- add css later -->
  <body>
    <div id="search">
      <header>
        <h1>Brewery Finder</h1>
      </header>

      <div>
        <form class="navElement" v-on:submit.prevent="listByZip">
          <label for="zip">Search by zip code</label>
          <input type="text" id="zip" placeholder="Search" v-model="zip" />
          <input type="submit" v-on:click="listByZip" placeholder="Submit" />
        </form>
        <form class="navElement" v-on:submit.prevent="listByCity">
          <label for="city">Search by city</label>
          <input type="text" id="city" placeholder="Search" v-model="city" />
          <input
            type="submit"
            v-on:click.prevent="listByCity"
            placeholder="Submit"
          />
        </form>
          <router-link tag='button' v-bind:to="{ name: 'addBrewery' }" v-show="this.$store.state.user.authorities[0].name != 'ROLE_USER'"
            >Add A Brewery</router-link
          >
      </div>
    </div>
    <div class="breweries">
      <div v-if="isLoading">
        <iframe src="https://giphy.com/embed/uUIjmgaHhGB4zMPdsz" width="480" height="480" frameBorder="0" class="giphy-embed" allowFullScreen></iframe><p><a href="https://giphy.com/gifs/party-beer-drink-uUIjmgaHhGB4zMPdsz">via GIPHY</a></p>
      </div>
      <div class="brewery" v-for="brewery in allBreweries" :key="brewery.id">
        <div class="breweryInfo">
          <router-link v-bind:to="{name: 'breweryDetails', params: {breweryId: brewery.id}}">
            <h3 class="name">{{ brewery.name }}</h3>
          </router-link>
          <h5 class="street">{{ brewery.street }}</h5>
          <h5 class="city state">{{ brewery.city }}, {{ brewery.state }}</h5>
        </div>
        <div class="breweryActions">
          <button v-on:click="updateBrewery(brewery.id)" v-if="currentUser === brewery.userId">update</button>
          <!--<button v-on:click="reviewBrewery(brewery.id)" v-if="currentUser !== brewery.userId">review</button>-->
        </div>
      </div>
    </div>

    
  </body>
</template>

<script>
import breweryService from "../services/BreweryService";

export default {
  data() {
    return {
      allBreweries: [],
      zip: "",
      city: "",
      isLoading: true,
      currentUser: this.$store.state.user.id,
    };
  },

  methods: {
    listByZip() {
      const zipcode = this.zip;
      this.allBreweries = [];
      console.log(zipcode);
      breweryService
        .searchByZipCode(zipcode)
        .then((brewery) => {
          this.isLoading = false;
          this.allBreweries = brewery.data;
        })
        .catch((err) => {
          console.error(err + "missing zip?");
        });
    },
    listByCity() {
      const searchCity = this.city;
      this.allBreweries = []; //when the function is called on click set the list to blank
      breweryService
        .searchByCity(this.cityFirstLetter(searchCity))
        .then((brewery) => {
          this.isLoading = false;
          this.allBreweries = brewery.data;
        })
        .catch((err) => {
          console.error(err + "missing city");
        });
    },
    //capitalize the first letter in the search
    cityFirstLetter(searchCity) {
      return searchCity.charAt(0).toUpperCase() + searchCity.slice(1);
    },
    updateBrewery(id){
      this.$router.push({name: "updateBrewery", params: {breweryId: id}})
    },
    reviewBrewery(id){
      this.$router.push({name: "reviewBrewery", params: {breweryId: id}})
    }
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");

body {
  display: flex;
  flex-direction: column;
}

body .breweries {
  display: flex;
  flex-direction: column;
  align-items: center;
}
div.brewery {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border: 3px rgb(1, 22, 39) solid;
  border-radius: 6px;
  width: 60%;
  min-width: 700px;
  padding: 1rem;
  margin: 10px;
  background-color: rgb(255, 159, 28, 0.7);
  color: rgb(1, 22, 39);
}

.name{
  color: rgb(1, 22, 39);
  font-family: "Montserrat", sans-serif;
}

#search div {
  display: flex;
  flex-grow: 2;
  align-items: center;
  justify-content: space-evenly;
}

#search {
  display: flex;
  background-color: rgba(46, 196, 181, 0.5);
}

h1 {
  font-size: 50px;
  font-family: "Montserrat", sans-serif;
  padding: 5px;
  margin: 25px;
}

label {
  display: block;
  padding-bottom: 10px;
  font-family: "Montserrat", sans-serif;
}

input {
  margin-right: 10px;
}

#search div > * {
  border: 3px rgb(46, 196, 182) solid;
  border-radius: 6px;
  padding: 20px;
}

.breweryActions button{
  font-size: 20px;
  margin: 10px;
}
</style>