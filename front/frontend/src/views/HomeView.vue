<template>
  <div class="home">
    <div class="products">
      <ProductInList v-for="product in products" :key="product.id" :product="product" />
    </div>
    <div class="pagination">
      <button @click="prevPage" :disabled="page === 0">Previous</button>
      <span>Page {{ page }}</span>
      <button @click="nextPage">Next</button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import ProductInList from '@/components/ProductInList.vue';
import ProductServiece from '@/services/ProductService.js';

export default {
  name: 'HomeView',
  components: {
    ProductInList
  },
  data() {
    return {
      products: [],
      page: 0
    };
  },
  created() {
    this.fetchProducts();
  },
  methods: {
    fetchProducts() {
      ProductServiece.getProducts(this.page)
        .then((res) => {
            this.products = res.data.content;
            console.log(res.data.content);
          }
        )
        .catch((err) => {
          console.error('Error fetching products: ', err);
        });
    },
    nextPage() {
      this.page++;
      this.fetchProducts();
    },
    prevPage() {
      if(this.page > 0){
        this.page--;
        this.fetchProducts();
      }
    }
  }
}
</script>

<style>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.products {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.pagination {
  margin: 16px;
}
</style>
