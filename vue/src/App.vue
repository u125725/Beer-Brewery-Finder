<template>
  <div id="app">
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
    />
    <div id="nav">
      <router-link
        class="link"
        v-if="$store.state.token == ''"
        v-bind:to="{ name: 'home' }"
        >Home</router-link
      >
      <!--Add homeAccount in following router link 
      when we get a user page working-->
      <router-link
        class="link"
        v-if="$store.state.token != ''"
        v-bind:to="{ name: 'home' }"
        >Home</router-link
      >
      <router-link class="link" v-bind:to="{ name: 'breweries' }"
        >Breweries</router-link
      >
      <router-link class="link" v-bind:to="{ name: 'beers' }"
        >Beers</router-link
      >
      <div class="dropdown" v-if="$store.state.token != ''">
        <button class="dropbtn">Account</button>
        <div class="dropdown-content">
          <router-link class="link" v-bind:to="{ name: 'logout' }"
            >Logout</router-link
          >
          <button id="deleteBtn" class="link" v-on:click="deleteUser">
            Delete
          </button>
        </div>
      </div>
      <router-link
        class="link"
        v-bind:to="{ name: 'login' }"
        v-if="$store.state.token == ''"
        >Login</router-link
      >
    </div>
    <router-view />
  </div>
</template>

<script>
import authService from "../src/services/AuthService.js";

export default {
  data() {
    return {
      username: this.$store.state.user.username,
    };
  },
  methods: {
    deleteUser() {
      //let choice = confirm("Are you sure you want to delete your account?");
      //if (choice) {
        authService
          .delete(this.username)
          .then((response) => {
            console.log(response.status);
            if (response.status === 204) {
              this.$router.push({ name: "logout" });
            }
          })
          .catch((error) => console.log(error));
      //}
    },
  },
};
</script>
<!--<script>
import Map from './components/Map';
export default {
  name: 'App',
  components: {
    Map
  }
}
</script>-->
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");

#nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: rgb(231, 29, 54);
  height: 50px;
}

#deleteBtn {
  border: none;
  background: rgb(231, 29, 54);
  cursor: pointer;
}
.link {
  color: rgb(253, 255, 252);
  font-size: 20px;
  font-family: "Montserrat", sans-serif;
  text-decoration: none;
  padding-left: 20px;
  padding-right: 20px;
}

.dropbtn {
  border: none;
  background-color: rgb(231, 29, 54);
  color: rgb(253, 255, 252);
  font-size: 20px;
  padding: 10px;
  margin-left: 20px;
  margin-right: 10px;
  font-family: "Montserrat", sans-serif;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: rgb(231, 29, 54);
  width: 135px;
}

.dropdown-content .link {
  display: block;
  padding: 12px 30px;
}

.dropdown:hover .dropdown-content {
  display: block;
}
</style>
