<template>
   <div class="listaUsera">
      <div v-for="korisnik in korisnici" :key="korisnik.id" :korisnik="korisnik" class="userJedan" @click="openUser(korisnik.id)">
         <img :src="korisnik.slika" alt="avatar">
         <h3>{{ korisnik.korisnickoIme }}</h3>
         <p> {{ korisnik.uloga }} </p>
         <p> {{ korisnik.opis }} </p>
      </div>
   </div>
</template>

<script>
import axios from 'axios';

export default {
   name: 'UsersComponent',
   data(){
      return {
         korisnici: []
      }
   },
   mounted() {
      axios
         .get('http://localhost:8081/korisnici', {withCredentials: true})
         .then((res) => {
            this.korisnici = res.data;
         })
         .catch((err) => {
            if(err.response.status === 403) alert('morate biti ulogovani!');
            else alert('doslo je do grreske');
            this.$router.push('/');
         })
   },
   methods: {
      openUser(id){
         console.log(id);
         this.$router.push({
            name: 'otherUser',
            params: { korisnikId: id}
         })
      }
   }
}
</script>

<style scoped>
   .userJedan{
      display: flex;
      flex-direction: column;
      width: 20%;
      justify-content: center;
      text-align: center;
      background-color: #42b883;
      padding: 1%;
      border-radius: 10px;
      box-shadow: 2px 2px #246347e2;
   }
   .listaUsera{
      display: flex;
      flex-wrap: wrap;
      justify-content: space-around;
   }
</style>