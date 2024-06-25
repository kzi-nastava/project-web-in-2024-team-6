<template>
   <h2>Moj profil</h2>
   <Profile />
</template>

<script>
   import Profile from '@/components/UserComponent.vue';

   export default {
      name: 'UserView',
      components: {
         Profile
      }
   }
</script>