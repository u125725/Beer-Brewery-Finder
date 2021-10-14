import axios from 'axios';

// const http = axios.create({
//     baseURL: "https://localhost:8080"
// });

export default{
    listAllBreweries(){
        return axios.get('/allBreweries');
    },
    searchByZipCode(zip){
        return axios.get(`/searchByZip/${zip}`);
    },
    searchByCity(city){
        return axios.get(`/searchByCity/${city}`);
    },
    addBrewery(brewery){
        return axios.post('/addBrewery', brewery);
    },
    getBreweryDetails(id){
        return axios.get(`/breweries/${id}`);
    },
    updateBrewery(id, brewery){
        return axios.put(`/updateBrewery/${id}`, brewery);
    },
    addReview(id, review){
        return axios.post(`/breweries/${id}`, review);
    }

}