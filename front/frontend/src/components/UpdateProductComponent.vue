<template>
   <div class="proizvodA">
      <label>naziv:</label>
      <input type="text" v-model="proizvod.naziv">

      <label>opis:</label>
      <input type="text" v-model="proizvod.opis">

      <label>naziv:</label>
      <input type="text" v-model="proizvod.naziv">

      
      <label>kategorija:</label>
      <input type="text" v-model="proizvod.kategorija.naziv">

      <label>cena:</label>
      <input type="number" v-model="proizvod.cena">

      <label>slika:</label>
      <input type="text" v-model="proizvod.slika">

      <label>naziv:</label>
      <input type="text" v-model="proizvod.naziv">

      <label>tip prodaje:</label>
      <input type="text" v-model="proizvod.tipProdaje">

      <button>Izmeni</button>
   </div>
</template>

<script>
import axios form 'axios';

export default {
   name: 'updateProd',
   data(){
      proizvod: {}
   }
}
</script>