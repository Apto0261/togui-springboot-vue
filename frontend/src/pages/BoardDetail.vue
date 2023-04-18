<template>
  <div class="board-write">
    <div class="form-group">
      <label for="title">제목</label>
      <input name="title" type="text" class="form-control" id="title" placeholder="제목" v-model="state.items.title" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" id="content" name="content" rows="10" v-model="state.items.content" readonly="readonly"></textarea>
    </div>
    <div class="form-group">
      <input type="file" class="form-control-file" id="upload-file">
    </div>
    <br>
    <div class="save-btn">
      <button class="btn btn-primary mb-2" @click="goBack()">목록</button>
    </div>
  </div>
</template>

<script>
import { reactive } from 'vue'
import { useRoute } from 'vue-router';
import axios from 'axios'
import router from '@/scripts/router';

export default {
  setup(){
    const state = reactive({
      items:[]
    });

    const route = useRoute();

    const goBack = () =>{
      router.push({path:"/board"});
    }

    const load = () => {
      axios.get("/api/board/detail/" + route.query.key)
      .then(({ data }) => {
          console.log(data);
          state.items = data;
      });
    }

    load();

    return {state,goBack}
    
  }
}
</script>

<style scoped>

.form-group {
  margin-bottom: 20px;
}

.form-group label{
  margin-bottom: 10px;
}
.save-btn{
  float: right;
}

</style>
