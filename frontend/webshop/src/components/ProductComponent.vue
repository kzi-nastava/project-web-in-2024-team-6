<template>
   <div clss="product">
      <img :src="product.slika" alt="slikaProizvoda">
      <h3>{{ product.naziv }}</h3>
      <p>{{ product.opis }}</p>
      <p>{{ product.cena }} RSD</p>
   </div>
      
</template>

<script>
export default{
   name: 'product',
   props: {
      product: {
         type: Object,
         required: true
      }
   }
};
</script>

<style scoped>
.product{
   border: 1px solid #ccc;
   padding: 16px;
   margin: 4%;
   width: 100%;
   background-color: #ccc;
}
.product img {
   width: 100%;
}
</style>