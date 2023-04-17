<template>
  <div class="login">
    <main class="form-signin w-100 m-auto">
        <h1 class="h3 mb-3 fw-normal">로그인</h1>

        <div class="form-floating">
          <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" v-model="state.form.email">
          <label for="floatingInput">이메일</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="state.form.password">
          <label for="floatingPassword">비밀번호</label>
        </div>
        <hr>
        <div class="d-grid gap-2">
          <button class="w-100 btn btn btn-outline-primary" @click="submit()">로그인</button>
          <button class="w-100 btn btn btn-outline-success" @click="register()">회원가입</button>
        </div>
    </main>
  </div>
</template>

<script>
import router from '@/scripts/router';
import { reactive } from 'vue'
import axios from 'axios'
import store from '@/scripts/sotre';

export default {
  setup(){
    const state = reactive({
      form:{
        email:"",
        password:""
      }
    });

    const submit = () => {
      axios.post("/api/login",state.form).then((res) => {
        console.log(res.data);
        router.push({path:"/"});
        sessionStorage.setItem("token",res.data.token);
        store.commit("setAccount", res.data);
      }).catch((err)=>{
        window.alert(err.message);
      })
    }

    const register = () => {
      router.push({path:"/register"});
    }

    return {state,submit,register}
    
  }

}
</script>

<style scoped>
.login{
  margin-top: 150px;
  margin-bottom: 150px;
}
.form-signin {
  max-width: 330px;
  padding: 15px;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
