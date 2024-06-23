import axios from 'axios';

const urlApi = 'http://localhost:8081/api/products/searchedNFiltered';

export default {
   getProducts(page, condtion){
      return axios.post(urlApi, condtion, { params: {strana: page}});
   }
};