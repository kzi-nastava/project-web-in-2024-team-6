<template>
  <div class="container flex flex-col items-center justify-center mx-auto">
    <button
      class="mt-8 rounded bg-red-800 text-white w-56 h-10 shadow-lg"
      @click="handleLogout"
    >
      Logout
    </button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    async handleLogout() {
      try {
        const response = await axios.post(
          "http://localhost:8080/logout",
          {},
          {
            withCredentials: true,
            headers: {
              "Content-Type": "application/json",
            },
          },
        );
        if (response.status === 200) {
          alert("You have successfully logged out.");
          this.$router.push("/login");
        } else {
          alert("Logout action not allowed.");
        }
      } catch (error) {
        if (error.response && error.response.status === 403) {
          alert("Logout action not allowed.");
        } else {
          console.error(error);
          alert("An error occurred during logout.");
        }
      }
    },
  },
};
</script>

<style></style>
