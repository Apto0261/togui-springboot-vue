<template>
  <div class="container py-3">
    <Header></Header>
    <RouterView/>
    <Footer></Footer>
  </div>
</template>

<script>
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
import store from './scripts/sotre';
import axios from 'axios';
import { watch } from 'vue';
import { useRoute } from 'vue-router';

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  setup() {
    const check = () => {
      axios.get("/api/check").then(({data})=>{
        if (data) {
          console.log(data)
          store.commit("setAccount",data || 0);
        }
      })
    };

    const route = useRoute();

    watch(route, ()=> {
      check();
    })
  }
}
</script>

<style scoped>
.container {
  max-width: 960px;
}


</style>
