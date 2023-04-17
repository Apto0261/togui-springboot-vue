<template>
  <div class="board">
    <table class="table table-striped-columns">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>날짜</th>
          <th>조회수</th>
        </tr>
      </thead>
      <tbody class="table-group-divider">
        <tr v-for="(i,idx) in state.items" :key="idx">
          <td>{{ i.id }}</td>
          <td>{{ i.title }}</td>
          <td>{{ i.nickname }}</td>
          <td>{{ i.createDate }}</td>
          <td>{{ i.viewCnt }}</td>
        </tr>
      </tbody>
    </table>
    <div>
      <router-link to="/board/write">글쓰기</router-link>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import axios from 'axios'
export default {
  setup(){
    const state = reactive({
            items:[]
    });

    const load = () => {
        axios.get("/api/board/list",{
          headers: {
            Authorization: `Bearer ${sessionStorage.getItem("token")}`
          }
        }).then(({data}) =>{
            console.log(data);
            state.items = data;
        }).catch((err)=>{
          window.alert(err.message);
        });
    };
    
    load();

    return {state}
  }

}
</script>

<style scoped>

</style>
