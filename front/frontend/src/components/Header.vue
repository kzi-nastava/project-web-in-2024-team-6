<template>
   <header>
     <img alt="logo" src="../assets/logo.png" class="logo"/>
     <nav>
       <router-link to="/">Home</router-link> |
       <router-link to="/about">About</router-link>
     </nav>
     <div class ="loginRegister" >
       <button v-if="!loggedIn">
         Login
       </button>
       <button v-if="!loggedIn">
         Register
       </button>
       <button v-if="loggedIn">
        Logout
      </button>
     </div>
   </header>
 </template>

<script>
import axios from 'axios';
 export default {
   name: 'Header',
   data() {
      return{ loggedIn: false};
   },
   mounted(){
      this.chechLoginStatus();
   },
   methods: {
      chechLoginStatus() {
        axios
          .get("http://localhost:8081/api/isLoged", {withCreditentials: true})
          .then((res) =>{
              if(res.data){
                this.loggedIn = true;
              } else {
                this.loggedIn = false;
              }
          })
          .catch(
            this.loggedIn = false
          );
      }
   }
 };
</script>

<style>
.loginRegister {
   display: flex;
   margin-right: 1%;
   width: 20%;
   justify-content: space-around;
   
}

.loginRegister button {
 width: 6em;
 height: 2.5em;
}

header {
 display: flex;
 justify-content: space-between;
 align-items: center;
 height: 4em;
}

.logo {
 height: 80%;
 margin-left: 1%;
}
</style>