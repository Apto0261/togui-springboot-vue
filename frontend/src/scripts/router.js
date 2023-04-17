import { createRouter, createWebHistory } from 'vue-router';
import Main from "@/pages/Main"
import Login from "@/pages/Login"
import Board from "@/pages/Board"
import BoardWrite from "@/pages/BoardWrite"

const routes = [
    {path:'/', component: Main},
    {path:'/login', component: Login},
    {path:'/board', component: Board},
    {path:'/board/write', component: BoardWrite},

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router