<template>
  <div class="board-write">
    <div class="form-group">
      <label for="title">제목</label>
      <input name="title" type="text" class="form-control" id="title" placeholder="제목" v-model="state.form.title">
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textarea class="form-control" id="content" name="content" rows="10" v-model="state.form.content"></textarea>
    </div>
    <div class="form-group">
      <input type="file" class="form-control-file" id="upload-file">
    </div>
    <br>
    <div class="save-btn">
      <button class="btn btn-primary mb-2" @click="write()">완료</button>
    </div>
  </div>
</template>

<script>
import router from '@/scripts/router';
import { reactive } from 'vue'
import axios from 'axios'

export default {
  setup(){
    const state = reactive({
      form:{
        title:"",
        content:""
      }
    });

    const write = () => {
      console.log("보내기전 ~ " + sessionStorage.getItem("token"));
      axios.post("/api/board/write"
                  ,state.form
                  ,{
                    headers: {
                      Authorization: `Bearer ${sessionStorage.getItem("token")}`
                    }
                  }
      ).then((res) => {
        console.log(res.data);
        router.push({path:"/board"});
      }).catch((err)=>{
        window.alert(err.message);
      })
    }

    return {state,write}
    
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
