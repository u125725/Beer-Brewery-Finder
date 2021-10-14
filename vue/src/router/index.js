import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import HomeAccount from '../views/HomeAccountView.vue'
import Login from '../views/LoginView.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import ListOfBreweries from '../views/ListOfBreweriesView.vue'
import AddBrewery from '../views/AddBreweryView.vue'
import ListOfBeers from '../views/ListOfBeersView.vue'
import BreweryDetails from '../views/BreweryDetailsView.vue'
import BeerDetails from '../views/BeerDetailsView.vue'
import AddBeer from '../views/AddBeerView.vue'
import AddReview from '../views/AddReviewView.vue'
import UpdateBeer from '../views/UpdateBeerView.vue'
import UpdateBrewery from '../views/UpdateBreweryView.vue'
import ReviewBrewery from '../views/AddReviewBreweryView.vue'
//import Map from '../views/Map.vue'
//import Locations from '../views/Locations.vue'
Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/',
      name: 'homeAccount',
      component: HomeAccount,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/breweries",
      name: "breweries",
      component: ListOfBreweries,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/breweries/add",
      name: "addBrewery",
      component: AddBrewery,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/beers",
      name: "beers",
      component: ListOfBeers,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/breweries/:breweryId",
      name: 'breweryDetails',
      component: BreweryDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/breweries/:breweryId/beers/:beerId",
      name: 'beerDetails',
      component: BeerDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/breweries/:breweryId/addBeer",
      name: "addBeer",
      component: AddBeer,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/beers/:beerId/review",
      name: "addReview",
      component: AddReview,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/beers/:beerId/update",
      name: "updateBeer",
      component: UpdateBeer,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/breweries/:breweryId/update",
      name: "updateBrewery",
      component: UpdateBrewery,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/breweries/:breweryId/review",
      name: "reviewBrewery",
      component: ReviewBrewery,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
