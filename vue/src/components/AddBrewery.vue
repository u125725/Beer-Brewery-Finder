<template>
  <body>
    <div class="spacer"></div>
    <form v-on:submit.prevent="addNewBrewery">
      <div class="form-element">
        <label for="name">Name:</label>
        <input id="name" type="text" v-model="newBrewery.name" />
      </div>
      <div class="form-element">
        <label for="street">Address:</label>
        <input id="street" type="text" v-model="newBrewery.street" />
      </div>
      <div class="form-element">
        <label for="city">City:</label>
        <input id="city" type="text" v-model="newBrewery.city" />
      </div>
      <div class="form-element">
        <label for="state">State:</label>
        <select id="state" v-model="newBrewery.state">
          <option>Alabama</option>
          <option>Alaska</option>
          <option>Arizona</option>
          <option>Arkansas</option>
          <option>California</option>
          <option>Colorado</option>
          <option>Connecticut</option>
          <option>Delaware</option>
          <option>District Of Columbia</option>
          <option>Florida</option>
          <option>Georgia</option>
          <option>Hawaii</option>
          <option>Idaho</option>
          <option>Illinois</option>
          <option>Indiana</option>
          <option>Iowa</option>
          <option>Kansas</option>
          <option>Kentucky</option>
          <option>Louisiana</option>
          <option>Maine</option>
          <option>Maryland</option>
          <option>Massachusetts</option>
          <option>Michigan</option>
          <option>Minnesota</option>
          <option>Mississippi</option>
          <option>Missouri</option>
          <option>Montana</option>
          <option>Nebraska</option>
          <option>Nevada</option>
          <option>New Hampshire</option>
          <option>New Jersey</option>
          <option>New Mexico</option>
          <option>New York</option>
          <option>North Carolina</option>
          <option>North Dakota</option>
          <option>Ohio</option>
          <option>Oklahoma</option>
          <option>Oregon</option>
          <option>Pennsylvania</option>
          <option>Rhode Island</option>
          <option>South Carolina</option>
          <option>South Dakota</option>
          <option>Tennessee</option>
          <option>Texas</option>
          <option>Utah</option>
          <option>Vermont</option>
          <option>Virginia</option>
          <option>Washington</option>
          <option>West Virginia</option>
          <option>Wisconsin</option>
          <option>Wyoming</option>
        </select>
      </div>
      <div class="form-element">
        <label for="postalCode">Zip:</label>
        <input id="postalCode" type="text" v-model="newBrewery.postalCode" />
      </div>
      <div class="form-element">
        <label for="websiteUrl">Website:</label>
        <input id="websiteUrl" type="text" v-model="newBrewery.websiteUrl" />
      </div>
      <div class="form-element">
        <label for="phone">Phone:</label>
        <input id="phone" type="text" v-model="newBrewery.phone" />
      </div>
      <button class="btn1 btn-submit">Submit</button>
      <button class="btn2 btn-cancel" v-on:click.prevent="cancelForm">
        Cancel
      </button>
    </form>
    <div class="spacer"></div>
  </body>
</template>

<script>
import breweryService from "../services/BreweryService.js";
export default {
  name: "add-brewery",
  data() {
    return {
      newBrewery: {
        name: "",
        street: "",
        city: "",
        state: "",
        postalCode: "",
        websiteUrl: "",
        phone: "",
        userId: this.$store.state.user.id, //gets the ID od the active user to add a brewery
      },
    };
  },
  methods: {
    addNewBrewery() {
      breweryService
        .addBrewery(this.newBrewery)
        .then((response) => {
          if (response.status === 200) {
            this.$router.push({ name: "breweries" });
          }
        })
        .catch((error) => console.log(error));
    },
    cancelForm() {
      this.$router.push({ name: "breweries" });
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");
body {
  display: flex;
  flex-direction: column;
}

.spacer {
  height: 15vh;
}

form {
  background-color: rgb(46, 196, 181, 0.5);
  text-align: center;
  width: 50%;
  margin-left: 25vw;
  border: 3px rgb(1, 22, 39) solid;
  border-radius: 6px;
  padding-bottom: 20px;
  padding-top: 10px;
}

.form-element {
  padding: 10px;
}

label {
  padding-right: 8px;
  font-family: "Montserrat", sans-serif;
}

input {
  padding: 5px;
  border: 0;
  border-radius: 10px;
  box-shadow: 0 0 15px 4px rgb(0, 0, 0, 0.06);
}


</style>