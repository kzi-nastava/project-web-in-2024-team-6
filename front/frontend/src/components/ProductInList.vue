<template>
   <!-- naziv, opis, kategorija, cena, slika i tip prodaje-->
   <div class="productDiv" @click="openDetail" >
      <img :src="product.slika" alt="slikaProizvoda">
      <h3>{{ product.naziv }}</h3>
      <p>{{ product.opis }}</p>
      <p>{{ product.cena }} RSD</p>
   </div>
   <div class="extendable"  v-if="extended"> 
      <div class="image">
         <img :src="product.slika" alt="slikaProizvoda">
      </div>
      <div class="proizvod">
         <h3>{{ product.naziv }}</h3>
         <p><b>Opis:</b> <br>{{ product.opis }}</p>
         <p><b>Kategorija:</b><br> {{ product.kategorija.naziv }} </p>
         <p><b>Cena:</b><br> {{ product.cena }} RSD</p>
         <p><b>Tip prodaje:</b><br> {{ product.tipProdaje }} </p>
      </div>
      <div class="buy">
         <div class="X" @click="openDetail">x</div>
         <div class="buyBtn" v-if="tipp" @click="prodBuy">
            Kupi odmah 
         </div>
         <div class="ponuda" v-if="!tipp">
            <label>Postavite ponudu</label>
            <input type="number" v-model="ponuda">
            <button @click="prodOffer">Predaj ponudu</button>
         </div>
      </div>
   </div>
      
</template>

<script>
import axios from 'axios';

export default{
   name: 'ProductInList',
   data(){
      return {extended: false, tipp: this.product.tipProdaje == 'fiksnaCena' ? true : false, ponuda: 0 }
   },
   props: {
      product: {
         type: Object,
         required: true
      }
   },
   methods: {
      openDetail(){
         console.log(this.tipp);
         this.extended = !this.extended;
      },
      prodBuy(){
         axios
            .post('http://localhost:8081/api/products/api/purchase', this.product, {withCredentials: true})
            .then((res) => {
               alert('Uspesno kupljen proizvod');
            })
            .catch((error) => {
               if(error.response.data === 'Prozivod ne postoji!'){
                  alert('Proizvod ne postoji')
               } else if (error.response.data === 'Zabranjen pristup'){
                  alert('Proizvod nije kupljen jer niste kupac')
               } else if (error.response.status === 500) alert('Proizvod nije kupljen jer je doslo do greske');
            });
      },
      prodOffer(){
         axios
            .post('http://localhost:8081/api/products/api/offer/' +  this.ponuda , this.product, {withCredentials: true})
            .then((res) => {
               alert('Uspesno postavljena ponuda za proizvod');
            })
            .catch((error) => {
               if(error.response.data === 'Prozivod ne postoji!'){
                  alert('Proizvod ne postoji')
               } else if (error.response.data === 'Zabranjen pristup'){
                  alert('Proizvod nije kupljen jer niste kupac')
               } else if (error.response.status === 500) alert('Proizvod nije kupljen jer je doslo do greske, probajte vecu ponudu');
            });
      }
   }
};
</script>

<style scoped>
.productDiv{
   padding: 16px;
   gap: 4%;
   width: 20%;
   position: relative;
   z-index: 1;
   background-color: #42b883;
   padding: 1%;
   border-radius: 10px;
   box-shadow: 2px 2px #246347e2;
   margin-top: 2%;
}
.extendable{
   padding: 16px;
   width: 80%;
   height: 80%;
   position: absolute;
   z-index: 10;
   transition: all 0.5s ease-in;
   background-color: #35495e;
   padding: 1%;
   border-radius: 10px;
   box-shadow: 2px 2px #1d2835d7;
   color: white;
   display: flex;
}
.productDiv img {
   width: 100%;
}

.image, .proizvod, .buy {
   display: flex;
   flex-direction: column;
   justify-content: space-around;
   width: 33.33%;
   height: 100%;
   font-size: 2em
}
.buy{
   position: relative;
}
.buyBtn {
   border: 1px solid white;
   background-color: white;
   color: #35495e;
   width: 60%;
   margin: auto;
   border-radius: 10px;
}
.buyBtn:hover {
   border: 1px solid #42b883;
   background-color: #42b883;
   color: white;
   transition: all 0.5s ease;
   cursor: pointer
}
.X{
   cursor: pointer;
   position: absolute;
   top: 1px;
   right: 1px;
}
</style>