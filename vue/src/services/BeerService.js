import axios from 'axios';

// const http = axios.create({
//     baseURL: "https://localhost:8080"
// });

export default{
    listBreweryBeers(id){
        return axios.get(`/breweries/${id}/beers`);
    },
    getBeerDetails(id){
        return axios.get(`/beers/${id}`);
    },
    addBeer(id, beer){
        return axios.post(`/breweries/${id}/addBeer`, beer);
    },
    listOfBeers(){
        return axios.get('/allBeers');
    },
    addReview(id, review){
        return axios.post(`/beers/${id}`, review);
    },
    listBeerReviews(id){
        return axios.get(`/beers/${id}/reviews`);
    },
    deleteBeer(id){
        return axios.delete(`deleteBeer/${id}`);
    },
    updateBeer(id, beer){
        return axios.put(`updateBeer/${id}`, beer);
    }
}