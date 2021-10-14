<template>
  <div>
    <h1 class="title">Beer List</h1>
    <div class="loading" v-if="isloading">
      <iframe
        src="https://giphy.com/embed/uUIjmgaHhGB4zMPdsz"
        width="480"
        height="480"
        frameBorder="0"
        class="giphy-embed"
        allowFullScreen
      ></iframe>
      <p>
        <a href="https://giphy.com/gifs/party-beer-drink-uUIjmgaHhGB4zMPdsz"
          >via GIPHY</a
        >
      </p>
    </div>
    <div>
      <!-- <input type="text" id="beerName" v-model="filter.name"> -->
      <div>
        <label>Beer Name:</label>
        <input type="text" v-model="filter.name" />
        <div v-for="beer in filterBeer" v-bind:key="beer.id" class="beers">
          <router-link
            v-bind:to="{
              name: 'beerDetails',
              params: { breweryId: beer.breweryId, beerId: beer.id },
            }"
            ><h2>{{ beer.name }}</h2></router-link
          >
          <h4>Category: {{ beer.category }}, ABV: {{ beer.abv }}</h4>
          <p>{{ beer.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import beerService from "../services/BeerService";
export default {
  data() {
    return {
      allBeers: {},
      isloading: true,
      filter: {
        name: "",
        category: "",
        abv: "",
        description: "",
      },
    };
  },
  created() {
    this.listBeers();
  },

  methods: {
    listBeers() {
      beerService
        .listOfBeers()
        .then((beer) => {
          this.isloading = false;
          this.allBeers = beer.data;
        })
        .catch((err) => {
          console.error(err + " missing the list of beers");
        });
    },
  },
  computed: {
    filterBeer() {
      let filtered = this.allBeers;
      if (this.filter.name != "") {
        filtered = filtered.filter((beer) =>
          beer.name.toLowerCase().includes(this.filter.name.toLowerCase())
        );
      }
      return filtered;
    },
  },
};
</script>

<style>
.beers {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border: 3px rgb(1, 22, 39) solid;
  border-radius: 6px;
  width: 200;
  margin: 10px;
  background-color: rgb(255, 159, 28, 0.7);
  color: rgb(1, 22, 39);
}

.title {
  display: flex;
  justify-content: center;
}

label {
  margin-left: 20px;
  margin-right: 10px;
}
</style>