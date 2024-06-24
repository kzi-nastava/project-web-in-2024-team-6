<template>
   <h2>Korisnik</h2>
   <SingleUserComponent :korisnikId="korisnikId"/>
</template>

<script>
   import SingleUserComponent from '@/components/SingleUserComponent';
   import { computed } from 'vue';

   export default {
      name: 'SingleUserView',
      components: {
         SingleUserComponent
      },
      props: {
         korisnikId: {
            type: String,
            required: true
         }
      }
   }
</script>