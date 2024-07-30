<template>
   <div class="registerCount">
      <label>Izaberite ulogu</label>
      <select v-model="korisnik.uloga" required>
         <option value="">
            izaberi ulogu   
         </option>
         <option>
            Kupac
         </option>
         <option>
            Prodavac
         </option>
      </select>
      <label>Unesite ime</label>
      <input v-model="korisnik.ime" type="text" required/>
      <label>Unesite prezime</label>
      <input v-model="korisnik.prezime" type="text" required/>
      <label>Unesite korisnicko ime</label>
      <input v-model="korisnik.korisnickoIme" type="text" required/>
      <label>Unesite mejl adresu</label>
      <input v-model="korisnik.mejlAdresa" type="email" required/>
      <label>Unesite broj telefona</label>
      <input v-model="korisnik.brojTelefona" type="number" required/>
      <label>Unesite lozinku</label>
      <input v-model="korisnik.lozinka" type="password" required/>
      <button @click="doRegister">Registruj se</button>
   </div>
</template>

<script>
   import axios from 'axios';

   export default{
      name: 'Register',
      data(){
         return{korisnik: {}}
      },
      methods: {
         doRegister() {
            console.log(this.korisnik);
            axios
            .post('http://localhost:8081/register', this.korisnik)
            .then((res) => {
               alert(res.data);
               this.$router.push('/login');
            })
            .catch((err) => {
               alert(err);
            });
         }
      }
   }
</script>

<style scoped>

   .registerCount {
      display: flex;
      flex-direction: column;
      width: 50%;
      margin: auto;
   }
   .registerCount label{
      margin-top: 2%;
   }
   .registerCount button{
      margin-top: 2%;
   }

</style>