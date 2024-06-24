<template>
   <div class="profile">
      <label>id:</label>
      <input type="number" v-model="korisnik.id" readonly>
      <label>Ime:</label>
      <input type="text" v-model="korisnik.ime">
      <label>Prezime:</label>
      <input type="text" v-model="korisnik.prezime">
      <label>Korisnicko ime:</label>
      <input type="text" v-model="korisnik.korisnickoIme">
      <label>Imejl adresa:</label>
      <input type="text" v-model="korisnik.mejlAdresa">
      <label>Broj telefona:</label>
      <input type="text" v-model="korisnik.brojTelefona">
      <label>Nova lozinka:</label>
      <input type="text" v-model="azuriranKorisnik.novaLozinka">
      <label>Datum rodjenja:</label>
      <input type="text" v-model="korisnik.datumRodjenja">
      <label>Putanja do slike:</label>
      <input type="text" v-model="korisnik.putanjaDoSlike">
      <label>Opis:</label>
      <textarea v-model="korisnik.opis">{{ korisnik.opis }}</textarea>
      <label>Uloga:</label>
      <input type="text" v-model="korisnik.uloga">
      <label>Prosecna ocena:</label>
      <input type="number" v-model="korisnik.prosecnaOcena">
      
      <button @click="update">
         Azuriraj Korisnika
      </button>
   </div>
</template>

<script>
   import axios from 'axios';

   export default {
      name: 'Profile',
      data() {
         return {
            korisnik: {},
            azuriranKorisnik: {},
            passOMT: "",
            idK: 0
         };
      },
      mounted() {
         this.getKorisnik();
      },
      methods: {
         getKorisnik() {
            axios
               .get('http://localhost:8081/korisnici/1', {withCredentials: true})
               .then((res) => {
                  this.korisnik = res.data;
               })
               .catch((err) => {
                  alert(err);
               })
         },
         update(){
            const input = prompt("Molimo ponovo unesite lozinku: ", this.passOMT);
            if(input !== null){
               this.passOMT = input;
               
               this.azuriranKorisnik.trenutnaLozinka = this.passOMT
               this.azuriranKorisnik.korisnickoIme = this.korisnik.korisnickoIme;
               this.azuriranKorisnik.email = this.korisnik.mejlAdresa;
               this.azuriranKorisnik.datumRodjenja = this.korisnik.datumRodjenja;
               this.azuriranKorisnik.profilnaSlika = this.korisnik.putanjaDoSlike;
               this.azuriranKorisnik.opis = this.korisnik.opis;
               this.azuriranKorisnik.brojTelefona = this.korisnik.brojTelefona;
               this.azuriranKorisnik.ime = this.korisnik.ime;
               this.azuriranKorisnik.prezime = this.korisnik.prezime;
               this.azuriranKorisnik.novaLozinka = this.korisnik.novaLozinka
               this.azuriranKorisnik.proizvodi = [];
               this.idK = this.korisnik.id;

               axios
               .put('http://localhost:8081/korisnici/' + this.idK, this.azuriranKorisnik, {withCredentials: true})
               .then((res) => {
                  alert('uspesno azuriranje');
                  this.$forceUpdate();
                  this.getKorisnik();
               })
               .catch((err) => {
                  alert('Neuspesno azuriranje, probajte ponovo');
               })
            } 
         }
      }
   }
</script>

<style scoped>
   .profile{
      display: flex;
      flex-direction: column;
      width: 50%;
      margin: auto;
   }
   .profile label{
      margin-top: 2%;
   }
   button {
      margin-top: 3%;
   }
</style>