<template>
  <body>
    <span id="intro">
      <div id="introText">
        <h1>Welcome To Brewery Finder!</h1>
        <p>
          If you love beer, then you have come to the right place. With Brewery
          Finder you can scour the country looking for the latest and greatest
          beer. You can also review certain beers to help everyone else find their
          dream beer. Just sign up today and get
          drinking!
        </p>
      </div>
      <div id="imageContainer">
        <img src="../../images/Craft_Beer_Picture.jpg" alt="Craft Beer Image" />
      </div>
    </span>
    <header id="favoritesHeader">
      <h1>Our Favorites</h1>
    </header>
    <div id="favorites">
      <div id="fav1" class="personalFav">
        <div class="favImg1"></div>
      </div>
      <div id="fav2" class="personalFav">
        <div class="favImg2"></div>
      </div>
      <div id="fav3" class="personalFav">
        <div class="favImg3"></div>
      </div>
      <div id="fav4" class="personalFav">
        <div class="favImg4"></div>
      </div>
    </div>
  </body>
</template>

<script>
export default {};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Montserrat:wght@500&display=swap");
body,
html {
  padding: 0;
  margin: 0;
  width: 100%;
  min-height: 100vh;
}

body {
  background-color: rgb(253, 255, 252);
}

#intro {
  padding-top: 10px;
  display: flex;
  justify-content: space-between;
}

#intro div {
  flex-basis: 100%;
}

#intro img {
  max-width: 90%;
  width: auto;
  height: auto;
}

h1 {
  font-size: 50px;
  font-family: "Montserrat", sans-serif;
  padding: 5px;
  margin: 25px;
  text-align: center;
}

p {
  font-size: 25px;
  font-family: "Montserrat", sans-serif;
  padding: 5px;
  margin: 25px;
}

#favorites {
  display: flex;
  justify-content: space-evenly;
  margin-top: 100px;
}

.personalFav {
  padding-left: 20px;
  padding-right: 20px;
}

.favImg1 {
  display: block;
  border: 3px rgb(46, 196, 182) solid;
  background-image: url(../../images/Elvis_Juice.png);
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: contain;
  border-radius: 50%;
  width: 200px;
  height: 200px;
}

.favImg2 {
  display: block;
  border: 3px rgb(46, 196, 182) solid;
  background-image: url(../../images/Milk_Stout.png);
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: contain;
  border-radius: 50%;
  width: 200px;
  height: 200px;
}

.favImg3 {
  display: block;
  border: 3px rgb(46, 196, 182) solid;
  background-image: url(../../images/Modelo-Negra.png);
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: contain;
  border-radius: 50%;
  width: 200px;
  height: 200px;
}

.favImg4 {
  display: block;
  border: 3px rgb(46, 196, 182) solid;
  background-image: url(../../images/Elvis_Juice.png);
  background-position: 50% 50%;
  background-repeat: no-repeat;
  background-size: contain;
  border-radius: 50%;
  width: 200px;
  height: 200px;
}

#introText {
  background-color: rgba(191, 204, 203, 0.5);
}

#imageContainer {
  background-color: rgb(191, 204, 203, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

#favoritesHeader{
  background-color: rgb(255, 159, 28, 0.7);
  color: rgb(1, 22, 39);
}

</style>