import { createApp } from 'vue'
import router from "@/scripts/router"
import App from './App.vue'
import store from './scripts/sotre'

// 다른 컴포넌트에서는 import 없이 this.$axios로 사용가능

createApp(App).use(store).use(router).mount('#app')
