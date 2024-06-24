<template>
   <div class="jedanKorisnik">
      <img src="#" alt="avatar">
      <h3>{{ korisnik.korisnickoIme}}</h3>
      <p>{{korisnik.ime}}</p>
      <p>{{korisnik.prezime}}</p>
      <p>{{korisnik.brojTelefona}}</p>
      <p>{{korisnik.mejlAdresa}}</p>
      <p>{{korisnik.uloga}}</p>
      <p>{{korisnik.opis}}</p>

      <h4>Priozvodi</h4>
      <div class="listaProizvoda" v-for="{proizvod, index} in korisnik.priozvodi" :key=index>
         <img :src="proizvod.slika" />
         <h5>{{ proizvod.naziv }}</h5>
         <p>{{ proizvod.opis }}</p>
         <p>{{ proizvod.cena }} RSD</p>
      </div>

      <h4>Recenzije</h4>
      <div class="listaRecenzija" v-for="{recenzija,index} in korisnik.recenzije" :key="index">
         <h5>{{ recenzija.korisnik.korisnickoIme }}</h5>
         <h6>{{ recenzija.ocena }}</h6>
         <p>{{ recenzija.komentar }}</p>
      </div>

      <h4>Prosecna ocena</h4>
      <h6>{{korisnik.prosenaOcena}}</h6>

   </div>
</template>

<script>
   import axios from 'axios';
   export default {
      name: 'SingleUserComponent',
      data(){
         return {
            korisnik: {
               priozvodi: [],
               recenzije: [],
               korisnickoIme: '',
               prezime: '',
               ime: '',
               brojTelefona: 0,
               mejlAdresa: '',
               uloga: '',
               opis: '',
               prosenaOcena: 0.0
            },
         }
      },
      props: {
         korisnikId: {
            type: String,
            required: true
         }
      },
      mounted() {
         console.log('res.data');
         axios
            .get('http://localhost:8081/korisnici/' + parseInt(this.korisnikId, 10), {withCredentials: true})
            .then((res) => {
               console.log(res.data);
               this.korisnik = res.data;
               
            })
            .catch((err) => {
               console.log('res.data2');
               alert(err);
            })
      }
   }
</script>