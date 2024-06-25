<template>
  <HeaderComp />
  <router-view />
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>

<script>
  import HeaderComp from './components/HeaderComponent.vue';

  export default {
    name: 'App',
    components: {
      HeaderComp
    }
  }
</script>