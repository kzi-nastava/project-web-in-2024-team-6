<template>
  <div class="container flex flex-col items-center justify-center mx-auto">
    <div class="text-center">
      <h2>Log In.</h2>
      <p class="flex flex-row"></p>
      <form @submit.prevent="handleLogin">
        <input
          class="border-2 border-gray-700 rounded-lg p-2"
          v-model="credentials.username"
          type="text"
          name="username"
          required
          placeholder="Uneste korisnicko ime"
        />
        <br />
        <br />
        <input
          class="border-2 border-gray-700 rounded-lg p-2"
          v-model="credentials.password"
          type="password"
          name="password"
          required
          placeholder="Unesite lozinku"
        />
      </form>
      <button
        class="mt-8 rounded bg-blue-800 text-white w-56 h-10 shadow-lg"
        @click="
          handleLogin();
          $emit('refresh');
        "
      >
        Login
      </button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: 'LoginPage',
  data() {
    return {
      credentials: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    closeDialog() {},
    async handleLogin() {
      try {
        const response = await axios.post(
          "http://localhost:8081/api/login",
          this.credentials,
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          },
        );
        if (response.status === 200) {
          this.$forceUpdate();
          this.$router.push("/");
          alert("Uspesno ste se ulogovali");
        } else if (response.status === 401) {
          alert("Korsinik sa ovim imenom nije pronadjen");
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          alert("Korsinik sa ovim imenom nije pronadjen");
        }
        console.error(error);
      }
    },
  },
};
</script>

<style></style>
