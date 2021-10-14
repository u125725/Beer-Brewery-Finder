<template>
  <div>
    <form v-on:submit.prevent="addNewBeer">
      <div class="form-element">
        <label class="name" for="name">Name:</label>
        <input id="name" type="text" v-model="newBeer.name" />
      </div>
      <div class="form-element">
        <label for="category">Category:</label>
        <select id="category" v-model="categoryAsString">
          <option value=1>British Ale</option>
          <option value=2>Irish Ale</option>
          <option value=3>North American Ale</option>
          <option value=4>German Ale</option>
          <option value=5>Belgian and French Ale</option>
          <option value=6>International Ale</option>
          <option value=7>German Lager</option>
          <option value=8>North American Lager</option>
          <option value=9>Other Lager</option>
          <option value=10>International Lager</option>
          <option value=11>Other Style</option>
        </select>
      </div>
      <div class="form-element">
        <label for="abv">Abv:</label>
        <input id="abv" type="text" v-model="newBeer.abv" />
      </div>
      <div class="form-element">
        <label for="ibu">Ibu:</label>
        <input id="ibu" type="text" v-model="newBeer.ibu" />
      </div>
      <div class="form-element">
        <label for="description">Description:</label>
        <textarea id="description" v-model="newBeer.description"></textarea>
      </div>
      <button class="btn1 btn-submit">Submit</button>
      <button class="btn2 btn-cancel" v-on:click.prevent="cancelForm">
        Cancel
      </button>
    </form>
  </div>
</template>

<script>
import beerService from "../services/BeerService.js"

export default {
  data() {
    return {
      newBeer: {
        breweryId: -1,
        catId: this.categoryAsString,
        
      },
      breweryId: -1,
      categoryAsString: ''
    }
  },
  methods: {
    addNewBeer() {
      this.newBeer.catId = parseInt(this.categoryAsString);
      beerService.addBeer(this.breweryId, this.newBeer).then(
        (response) => {
          if (response.status === 200) {
            this.$router.push({name: "breweryDetails", params: {breweryId: this.breweryId} })
          }
        }
      )
      .catch(
        (error) => console.log(error)
      );
    },
    cancelForm() {
      this.$router.push({ name: "breweryDetails", params: {breweryId: this.breweryId} });
    }
  },
  created() {
    this.breweryId = this.$route.params.breweryId;
    this.newBeer.breweryId = this.$route.params.breweryId;
  }
};
</script>

<style scoped>

form {
  background-color: rgb(46, 196, 181, 0.5);
  text-align: center;
  width: 50%;
  margin-left: 25vw;
  border: 3px rgb(1, 22, 39) solid;
  border-radius: 6px;
  padding-bottom: 20px;
  padding-top: 10px;
  margin-top: 100px;
}

.name {
  margin: 10px;
}

</style>