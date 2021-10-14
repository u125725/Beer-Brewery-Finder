<template>
  <div>
    <form v-on:submit.prevent="addReview">
      <div class="form-element">
        <label for="review">Review:</label>
        <textarea id="review" v-model="newReview.review"></textarea>
      </div>
      <div class="form-element">
        <label for="like"></label>
        <button class="like" id="like" v-on:click="switchLike" disabled>
          <i class="glyphicon glyphicon-thumbs-up"></i>
        </button>
      </div>
      <div class="form-element">
        <label for="dislike"></label>
        <button id="dislike" v-on:click="switchLike">
          <i class="glyphicon glyphicon-thumbs-down"></i>
        </button>
      </div>
      <button class="btn1 btn-submit">Submit</button>
      <button class="btn2 btn-cancel" v-on:click.prevent="cancelForm">
        Cancel
      </button>
    </form>
  </div>
</template>

<script>
import beerService from "../services/BeerService.js";
export default {
  data() {
    return {
      newReview: {
        userId: this.$store.state.user.id,
        
      },
      beer: {},
      beerId: -1,
      isLike: true,
    };
  },
  created() {
    this.beerId = this.$route.params.beerId;
    this.newReview.beerId = this.beerId;
    beerService.getBeerDetails(this.beerId).then((response) => {
      this.beer = response.data;
    });
  },
  methods: {
    switchLike() {
      if (this.isLike === false) {
        this.isLike = true;
        let button = document.getElementById("like");
        button.classList.add("like");
        button.disabled = true;
        button = document.getElementById("dislike");
        button.classList.remove("dislike");
        button.disabled = false;
      } else {
        this.isLike = false;
        let button = document.getElementById("dislike");
        button.classList.add("dislike");
        button.disabled = true;
        button = document.getElementById("like");
        button.classList.remove("like");
        button.disabled = false;
      }
    },
    addReview() {
      
      this.newReview.rating = this.isLike;
      beerService.addReview(this.beerId, this.newReview).then((response) => {
        if (response.status === 200) {
          this.$router.push({ name: "breweryDetails", params: {breweryId: this.beer.breweryId} });
        }
      });
    },
    cancelForm() {
      this.$router.push({ name: "breweryDetails", params: {breweryId: this.beer.breweryId} });
    }
  },
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

.like {
  background-color: rgb(37, 180, 37);
}

.dislike {
  background-color: rgb(209, 39, 39);
  
}

#dislike{
    margin-bottom: 10px;
}

textarea{
    min-width: 500px;
    min-height: 100px;
}

</style>