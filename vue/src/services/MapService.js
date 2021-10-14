import axios from 'axios';

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
    listLatitude(latitude){
        return axios.get(`/listLatitude/${latitude}`);
   

},
   listLongitude(longitude){
       return axios.get('/listLongitude/${longitude}');

    }
}