<template>
  <button v-if="!sif" class="prikaziFiltere" @click="showSNF">
    Prikazi filtere  
  </button>
  <button @click="submit">Update </button>
  <div v-if="sif" class="serachFilterBox">
    <div>
      <label>Ime ili opis:</label><br>
      <input type="text" v-model="searchDto.imeopis">
    </div>
    <div>
      <label>Cena od: </label><br>
      <input type="number" v-model="searchDto.cenaod">
    </div>
    <div>
      <label>Cena do: </label><br>
      <input type="number" v-model="searchDto.cenado">
    </div>
    <div>
      <label>Kategorija: </label><br>
      <select v-model="searchDto.kategorija.id"> 
        <option v-for="option in options" :key="option.id" :value="option.id" @change="findRightCategory">
           {{ option.naziv }}
        </option>
     </select>
    </div>
    <div>
      <label>Tip prodaje: </label><br>
      <select v-model="searchDto.tipP">
        <option value="">
           izaberi tip prodaje
        </option> 
        <option>
           aukcija
        </option>
        <option value="fiksnaCena">
           fiksna cena
        </option>
     </select>
    </div>
    <div>
      <label>Flegovi </label><br>
      <input type="text" v-model="searchDto.flagovi">
    </div>
    <button @click="submit">
       Trazi
    </button>
    <button @click="showSNF">
      Zatvori filtere
   </button>
 </div>
  <div class="home">
    <div class="products">
      <ProductInList v-for="product in products" :key="product.id" :product="product" />
    </div>
    <div class="pagination">
      <button @click="prevPage" :disabled="page === 0">Previous</button>
      <span>Page {{ page }}</span>
      <button @click="nextPage" :disabled="page === totalPages - 1">Next</button>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import ProductInList from '@/components/ProductInList.vue';
import ProductServiece from '@/services/ProductService.js';
import axios from 'axios';

export default {
  name: 'HomeView',
  components: {
    ProductInList
  },
  data() {
    return {
      products: [],
      page: 0,
      totalPages: 0,
      options: [],
      idKat: 0,
      searchDto: {
          imeopis: "",
          cenaod: 0,
          cenado: 100,
          kategorija: {
            id: 1,
            naziv: ""
          },
          tipP: "aukcija",
          flagovi: "00000"
      },
      sif: false
    };
  },
  mounted() {
    this.submit();
    this.plantSelect();
  },
  methods: {
    submit() {
      ProductServiece.getProducts(this.page, this.searchDto)
        .then((res) => {
            this.products = res.data.content;
            this.totalPages = res.data.totalPages;
            console.log(res.data.content);
          }
        )
        .catch((err) => {
          console.error('Error fetching products: ', err);
        });
    },
    nextPage() {
      if(page < this.totalPages){
        this.page++;
        this.fetchProducts();
      }
    },
    prevPage() {
      if(this.page > 0){
        this.page--;
        this.fetchProducts();
      }
    },
    plantSelect(){
      axios
          .get('http://localhost:8081/api/categories', {withCredentials: true})
          .then((res) => {
            this.options = res.data;
          })
          .catch((err) => {
            console.error('Error fetching categories: ', err);
          }
          );
    },
    showSNF() {
      this.sif = !this.sif;
    }
  }
}
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 3%
}
.products {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  width: 100%;
}
.pagination {
  margin: 16px;
}
.pagination button {
  margin-left: 1%;
  margin-right: 1%;
}
.serachFilterBox{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.serachFilterBox button{
  width: 10%;
  margin: 1% 1% 0.5% 1%;
}
</style>
