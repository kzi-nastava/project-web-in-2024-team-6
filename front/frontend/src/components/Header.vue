<template>
   <header @mousemove="chechLoginStatus">
     <img alt="logo" src="../assets/logo.png" class="logo" @click="redirectHome"/>
     <nav>
       <router-link to="/">Pocetna</router-link> |
       <router-link to="/users">Korisnici</router-link>
     </nav>
     <div class ="loginRegister" >
       <button v-if="!loggedIn" @click="redirect">
         Login
       </button>
       <button v-if="!loggedIn" @click="redirectRegister">
         Register
       </button>
       <button v-if="loggedIn" @click="redirectLogout">
        Logout
      </button>
      <div v-if="loggedIn" class="img_border">
        <router-link to='/user'>
          <img src="../assets/user.png" alt="avatar" class="avat" >
        </router-link>
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
          .get("http://localhost:8081/api/isLoged", {withCredentials: true})
          .then((res) =>{
              if(res.data){
                console.log(res.data);
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
      },
      redirectLogout(){
        axios 
          .post('http://localhost:8081/logout', {}, {withCredentials: true})
          .then((res) => {
            alert('uspesno ste se odlogovali');
            this.$router.push('/');
          })
          .catch((err) => {
            alert('Greska pri odlogvavanju:', err);
          });
      },
      redirectRegister(){
        this.$router.push('/register');
      },
      watch: {
        $route(to, from) {
            this.chechLoginStatus();
        }
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
  width: 30%;
}
.avat{
  height: 35px;
  margin-left: 5px;
}
</style>