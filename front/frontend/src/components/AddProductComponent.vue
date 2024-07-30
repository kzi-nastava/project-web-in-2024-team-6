<template>
   <!-- naziv proizvoda, opis,slika, cena, kategorija i tip prodaje-->
   <div>
      <label>Unesite naziv proizvoda</label>
      <input type="text" v-model="proizvod.naziv">
   </div>
   <div>
      <label>Unesite opis</label>
      <input type="text" v-model="proizvod.opis">
   </div>
   <div>
      <label>Postavite putanju za sliku</label>
      <input type="text" v-model="proizvod.slika">
   </div>
   <div>
      <label>Unesite cenu proizvoda</label>
      <input type="number" v-model="proizvod.cena">
   </div>
   <div>
      <label>Unesite kategoriju proizvoda</label>
      <input type="text" v-model="proizvod.kategorija.naziv">
   </div>
   <div>
      <label>Unesite tip prodaje proizvoda</label>
      <input type="text" v-model="proizvod.tipProdaje">
   </div>
   <button @click="submit">
      Dodaj proizvod
   </button>
</template>

<script>
   import axios from 'axios';

   export default {
      name: 'AddProduct',
      data(){
         return { 
            proizvod: {
               naziv: '',
               opis: '',
               slika: '',
               cena: 0,
               kategorija: {
                  naziv: ''
               },
               tipProdaje:''
            }};
      },
      mounted() {
         axios
            .get('http://localhost:8081/api/isProdavac', {withCredentials: true})
            .then((res) => {
               if(res.data === false) {
                  alert('Niste prodavac');
                  this.$router.push('/');
               }
            })
      },
      methods: {
         submit() {
            console.log(this.proizvod);
            axios
               .post('http://localhost:8081/api/products/add', this.proizvod, {withCredentials: true})
               .then((res) => {
                  alert('uspesno dodat proizvod');
                  this.$router.push('/');
               })
               .catch((err) => {
                  alert('niste prodavac');
                  this.$router.push('/');
               })
         }
      }
   }
</script>