<template>
    <div>
        <div style="max-width: 800px; margin: 0 auto; display: flex; align-items: center; justify-content: space-between">
            <div>
                <h1>Your coordinates:</h1>
                <p>{{ myCoordinates.lat }} Latitude, {{ myCoordinates.lng }} Longitude</p>
            </div>
            <div>
                <h1>Map coordinates:</h1> 
                <p>{{ mapCoordinates.lat }} Latitude, {{ mapCoordinates.lng }} Longitude</p>
            </div>
        </div>
        
        <GmapMap   
            :center="myCoordinates" 
            :zoom="zoom"
            style="width:640px; height:360px; margin: 32px auto;"
            ref="mapRef"
            @dragend="handleDrag"
        ></GmapMap>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                map: null,
                myCoordinates: {
                    lat: 0,
                    lng: 0
                  //creating object for data return - zeros as placeholders  
                },
                zoom: 7
            }
        },
        created() {
            // This if statement will check if the user has a saved center starting location
            if(localStorage.center) {
                this.myCoordinates = JSON.parse(localStorage.center);
            } else {
                // else will get a user's location from a browser request 
                this.$getLocation({})
                    .then(coordinates => {   //response will be passed through as a promise 
                        this.myCoordinates = coordinates;
                    })
                    .catch(error => alert(error)); //if user selects not to allow for location to be shared this takes care of the error
            }

            // now checking if user has a zoom saved location
            if(localStorage.zoom) {
                this.zoom = parseInt(localStorage.zoom);
            }
        },
        mounted() {
            // this is adding the geo location map to the data object which is 
            this.$refs.mapRef.$mapPromise.then(map => this.map = map);
        },
        methods: {
            handleDrag() {
                //centers everything and stores it in local storage
                let center = {
                    lat: this.map.getCenter().lat(),
                    lng: this.map.getCenter().lng()
                };
                let zoom = this.map.getZoom();

                localStorage.center = JSON.stringify(center);
                localStorage.zoom = zoom;
            }
        },
        computed: {
            mapCoordinates() {
                if(!this.map) {
                    return {
                        lat: 0,
                        lng: 0
                    };
                }

                return {
                    lat: this.map.getCenter().lat().toFixed(4),
                    lng: this.map.getCenter().lng().toFixed(4)
                }
            }
        }
    }
</script>