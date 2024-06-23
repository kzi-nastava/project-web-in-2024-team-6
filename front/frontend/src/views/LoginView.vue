<template>
   <div calss="loginFrom">
      <table @keyup.enter="login">
         <tr>
            <td>
               <label for="korisnickoIme" ><b>Korisnicko ime</b></label>
            </td>
         </tr>
         <tr>
            <td>
               <input v-model="loginDto.korisnickoIme">      
            </td>
         </tr>
         <br>
         <tr>
            <td>
               <label for="lozinka" ><b>Lozinka:</b></label>
            </td>
         </tr>
         <tr>
            <td>
               <input v-model="loginDto.lozinka" type="password">     
            </td>
         </tr>
         <br>
         <tr>
            <td colspan="2">
               <div @click="login" class="btn">
                  Login
               </div>
            </td>
         </tr>
      </table>
   </div> 
</template>

<script>
import axios from "axios";
export default {
  name: 'LoginView',
  data() {
   return {
      loginDto: {},
   };
  },
  methods: {
      login(){
         axios
            .post("http://localhost:8081/api/login", this.loginDto, {withCredentials: true})
            .then((res) => {
               console.log(res);
               this.$router.push("/");
               }
            )
            .catch((err) => {
                  console.log(err);
                  alert("Neuspesan login");
               }
            );
      },
  }
}
</script>

<style scoped>
   table{
      width: auto;
      margin-left: auto;
      margin-right: auto;
      margin-top: 5%;
      background-color: #80ff00;
      border-radius: 1%;
      padding: 1%;
      box-shadow: 1px 1px;
   }
   input {
      border-radius: 3rem;
      border: none;
      padding: 0.5rem;
      text-align: center;
      outline: none;
      margin: 10px;
      box-sizing: border-box;
      font-family: "Poppins", sans-serif;
      font-weight: 400;
   }
   input:hover {
      box-shadow: 0px 0px 8px -5px #000000;
    }
    input:active {
      box-shadow: 0px 0px 8px -5px #000000;
    }

   .btn {
      width: 50%;
      background-color: none;
      border-radius: 3rem;
      border: 1px solid white;
      margin: auto;
      height: 2em;
      text-align: center;
      vertical-align: middle;
   }
   .btn:hover{
      background-color: white;
   }
</style>