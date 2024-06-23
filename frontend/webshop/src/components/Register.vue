<template>
  <div class="registerpage">
    <h1>Molimo vas unesite podatke za registraciju.</h1>
    <form @submit.prevent="register">
      <input
        v-model="user.name"
        type="text"
        name="name"
        required
        placeholder="Unesite ime"
      />
      <br />
      <input
        v-model="user.lastname"
        type="text"
        name="lastname"
        required
        placeholder="Unesite prezime"
      />
      <br />
      <input
        v-model="user.username"
        type="text"
        name="username"
        required
        placeholder="Unesite korisnicko ime"
      />
      <br />
      <input
        v-model="user.password"
        type="password"
        name="password"
        required
        placeholder="Unesite lozinku"
      />
      <br />
      <input
        v-model="user.email"
        type="email"
        name="email"
        required
        placeholder="Unesite email"
      />
      <br />
      <input
        v-model="user.phoneNumber"
        type="text"
        name="phoneNumber"
        required
        placeholder="Unesite broj telefona"
      />
      <br />
      <input
        v-model="user.role"
        type="text"
        name="role"
        required
        placeholder="Unesite ulogu"
      />
      <br />
      <input type="submit" value="Register" />
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      user: {
        name: "",
        lastname: "",
        username: "",
        password: "",
        email: "",
        phoneNumber: "",
        role: "",
      },
    };
  },
  methods: {
    async register() {
      try {
        const response = await axios.post(
          "http://localhost:8081/register",
          this.user,
          {
            headers: {
              "Content-Type": "application/json",
            },
          },
        );
        if (response.status === 200) {
          this.$router.push("/about");
        } else {
          alert("Registracija neuspešna");
        }
      } catch (error) {
        if (error.response && error.response.status === 403) {
          alert("Nalog sa ovim imenom već postoji");
        } else {
          alert("Registracija neuspešna");
        }
      }
    },
  },
};
</script>

<style scoped></style>
