<template>
   <nav>
      <router-link to="/">
         <div class="logo">
            <img src="../assets/KIBS.png" alt="logoImg">
         </div>
      </router-link>
      <h1 class="naziv">KIBS</h1>
      <div class="navig">
         <router-link to="/">Pocetna</router-link>
         <router-link to="/users">Korisnici</router-link>
         <div class="btn" v-if="!loggedIn" @click="rLogin">Login</div>
         <div class="btn" v-if="!loggedIn" @click="rRegister">Register</div>
         <img src="" alt="avatar" v-if="loggedIn"/>
         <div class="btn" v-if="loggedIn" @click="rLogout">Logout</div>
      </div>
   </nav>
</template>

<script>
   import axios from 'axios';
   export default {
      name: 'HeaderComp',
      data() {
         return {loggedIn: false};
      },
      mounted() {
         this.isLogged();
      },
      methods: {
         isLogged() {
            axios
               .get('http://localhost:8081/api/isLoged', {withCreditenials: true})
               .then((result) => {
                  if(result.data) {
                     this.$forceUpdate();
                     this.loggedIn = true;
                  }
                  else this.loggedIn = false;
               }).catch((err) => {
                  console.log('Error with checking', err);
               });
         },
         rLogin(){
            this.$router.push('/login');
         },
         rRegister(){
            this.$router.push('/register');
         },
         rLogout(){
            this.$router.push('/logout');
         }
      },
   };
</script>

<style scoped>
   nav{
      display: flex;
      background-color: #7ED957;
      justify-content: space-between;
      height: 4rem;
      font-size: larger;
      text-align: center;
      width: 100%;
   }
   nav naziv{
      height: 100%;
      font-size: 4em;
   }
   .logo {
      height: 100%;
   }
   .logo img{
      height: 100%;
   }
   .navig{
      display: flex;
      justify-content: space-between;
      height: 100%;
      text-align: center;
      width: 30%;
   }
   .navig * {
      border: 1px solid #c9eeb9;
      border-radius: 10px;
      height: 50%;
      margin: 2%;
      padding-left: 5px;
      padding-right: 5px;
   }
   .navig *:hover {
      background-color: #c9eeb9;
   }
</style>