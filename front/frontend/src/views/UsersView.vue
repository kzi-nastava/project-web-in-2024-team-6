<template>
   <h2>Korisnici</h2>
   <UsersComponent />
</template>

<script>
   import UsersComponent from '@/components/UsersComponent.vue';

   export default {
      name: 'UsersView',
      components: {
         UsersComponent
      }
   }
</script>