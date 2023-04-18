import { createRouter, createWebHistory } from 'vue-router';
import Main from "@/pages/Main"
import Login from "@/pages/Login"
import Board from "@/pages/Board"
import BoardDetail from "@/pages/BoardDetail"
import BoardWrite from "@/pages/BoardWrite"
import Register from "@/pages/Register"

const routes = [
    {path:'/', component: Main},
    {path:'/login', component: Login},
    {path:'/register', component: Register},
    {path:'/board', component: Board},
    {path:'/boardDetail', component: BoardDetail},
    {path:'/board/write', component: BoardWrite},

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router