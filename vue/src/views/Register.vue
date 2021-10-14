<template>
  <body id="register" class="text-center">
     <div>
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      /> <br> <br>
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      /> <br> <br>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required/>
       <br> <br> 
       <input type="checkbox"
       v-bind:value="user.role"
       v-on:input="user.role === 'brewer' ? user.role = 'user' : user.role = 'brewer'">
       Is this a brewer's account? <br> <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account 
      </button> <br>
       <br> <br>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
    </div>
  </body>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
body {
  background-image: url("../../images/Craft_Beer_Picture.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  width: 100%;
  min-height: 100vh;
}

div {
   text-align: center;
  display: flex;
  justify-content: center;
}
 form {
  width: 300px;
  height: 470px;
  padding: 250px 40px;
  background: rgba(255,255,255,.7);
  margin-top: 100px;
  padding-top: 20px;
}

button{
  width: 100%;
}

</style>
