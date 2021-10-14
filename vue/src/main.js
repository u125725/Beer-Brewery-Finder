import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import VueGeolocation from 'vue-browser-geolocation';
// import * as VueGoogleMaps from 'vue2-google-maps'
// import vueGeolocation from 'vue-browser-geolocation'

// Vue.use(VueGoogleMaps, options:
//   load: {
//     key:'AIzaSyBcAETVuHwehRPIrjtr96XJZCafrRnzpos'
//   },
//     installComponents: false

Vue.use(VueGeolocation); //initializing VueGeolocation


Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
