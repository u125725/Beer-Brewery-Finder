<template>
  <div id="login" class="text-center">
    <div class="login">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <label for="username" class="sr-only"
        ></label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
      />

      <br><br>
      <label for="password" class="sr-only"
        ></label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
      />
      <div>
        <br>
      </div>
      <router-link :to="{ name: 'register' }">Need an account?</router-link> <br> <br>
      <button type="submit">Sign in</button>
    </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>

label {
  margin-left: 10px;
  margin-right: 10px;
  
}

.text-center {
  background-image: url("../../images/Craft_Beer_Picture.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
  min-height: 100vh;
  text-align: center;
}

form {
  width: 300px;
  height: 400px;
  padding: 120px 40px;
  background: rgba(255,255,255,.7);
  margin-top: 100px;
  padding-top: 40px;
  
}

button{
  width: 100%;
}

.login {
  display: flex;
  align-content: center;
  justify-content: center;
}
</style>