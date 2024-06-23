import axios from 'axios';

const urlApi = 'http://localhost:8081/api/products';

export default {
   getProducts(page){
      return axios.get(urlApi, { params: {strana: page}});
   }
};