import { createRouter, createWebHistory } from 'vue-router'
import MainView from '../components/MainView.vue'
import HelloWorld from '../components/HelloWorld.vue'


const routes = [
  {
    path: '/',
    name: 'hello',
    component: HelloWorld
  },
  {
    path: '/xepGiangVien',
    name: 'xepGiangVien',
    component: MainView
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
