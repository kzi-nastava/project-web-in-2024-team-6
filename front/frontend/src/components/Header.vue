<template>
   <header @click="chechLoginStatus">
     <img alt="logo" src="../assets/logo.png" class="logo" @click="redirectHome"/>
     <nav>
       <router-link to="/">Home</router-link> |
       <router-link to="/about">About</router-link>
     </nav>
     <div class ="loginRegister" >
       <button v-if="!loggedIn" @click="redirect">
         Login
       </button>
       <button v-if="!loggedIn">
         Register
       </button>
       <button v-if="loggedIn">
        Logout
      </button>
      <div v-if="loggedIn" class="img_border">
        <a>
          <img src="#" alt="avatar">
        </a>
      </div>
        
     </div>
   </header>
 </template>

<script>
import axios from 'axios';
 export default {
   name: 'Header',
   data() {
      return{ loggedIn: false };
   },
   mounted(){
      this.chechLoginStatus();
   },
   methods: {
      chechLoginStatus() {
        axios
          .get("http://localhost:8081/api/isLoged", {withCreditentials: true})
          .then((res) =>{
              if(res.data === true){
                this.$forceUpdate();
                this.loggedIn = true;
              } else {
                console.log(res.data);
                this.loggedIn = false;
              }
          })
          .catch((err) => {
            console.log(err);
            this.loggedIn = false;
          }
          );
      },
      redirect() {
        this.$router.push("/login");
      },
      redirectHome(){
        this.$router.push("/");
      }
   }
 };
</script>

<style>
.loginRegister {
   display: flex;
   margin-right: 1%;
}

.loginRegister button {
 width: 6em;
 height: 2.5em;
 margin: 1%;
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
.img_border{
  height: 70%;
  border-radius: 50%;
}
</style>