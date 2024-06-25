<template>
   <h2>Dodaj novi proizvod</h2>
   <AddProduct />
</template>

<script>
   import AddProduct from '@/components/AddProductComponent.vue';

   export default {
      name: 'AddProductView',
      components: {
         AddProduct
      }
   }
</script>