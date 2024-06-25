import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import RegisterView from '@/views/RegisterView.vue';
import UserView from '@/views/UserView.vue';
import UsersView from '@/views/UsersView.vue';
import SingleUserView from '@/views/SingleUserView.vue';
import AddProductView from '@/views/AddProductView.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterView
  },
  {
    path: '/user',
    name: 'user',
    component: UserView
  },
  {
    path: '/users',
    name: 'users',
    component: UsersView
  },
  {
    path: '/addProduct',
    name: 'addProduct',
    component: AddProductView
  },
  {
    path: '/otherUser/:korisnikId',
    name: 'otherUser',
    component: SingleUserView,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
