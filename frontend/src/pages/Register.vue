<template>
  <div class="register">
    <div class="form-group">
      <label for="register-nickname">닉네임</label>
      <input name="register-nickname" type="text" class="form-control" id="register-nickname" v-model="state.form.nickname">
      <button type="button" class="btn btn-warning" @click="isValidNickname(state.form.nickname)" @keyup="nicknamereset()">닉네임 중복</button>
    </div>
    <div class="form-group">
      <label for="register-email">이메일</label>
      <input name="register-email" type="text" class="form-control" id="register-email" v-model="state.form.email">
      <button type="button" class="btn btn-warning" v-if="!emailChk" @click="isValidEmail(state.form.email)" @keyup="emailreset()">이메일 중복</button>
    </div>
    <div class="form-group">
      <label for="register-name">이름</label>
      <input name="register-name" type="text" class="form-control" id="register-name" v-model="state.form.name">
    </div>
    <div class="form-group">
      <label for="register-password">비밀번호</label>
      <input name="register-password" type="password" class="form-control" id="register-password" v-model="state.form.password">
    </div>
    <div class="form-group">
      <label for="register-password-confirm">비밀번호 확인</label>
      <input name="register-password-confirm" type="password" class="form-control" id="register-password-confirm" v-model="state.form.passwordConfirm">
    </div>
    <br>
    <div class="save-btn">
      <button class="btn btn-primary mb-2" @click="submit()">회원가입</button>
    </div>
  </div>
</template>

<script>
import router from '@/scripts/router';
import { reactive } from 'vue'
import axios from 'axios'

export default {
  setup(){
    let nicknameChk = false;
    let emailChk = false;

    const state = reactive({
      form:{
        nickname:"",
        name:"",
        email:"",
        password: "",
        passwordConfirm: ""
      }
    });

    const isValidNickname = (nickname) => {
      axios.get("/api/register/nicknamechk/" + nickname)
      .then((res) => {
        if(!res.data){
          nicknameChk = true;
          alert("사용 가능한 닉네임 입니다.")
        }else {
          nicknameChk = false;
          alert("이미 존재하는 닉네임 입니다.")
        }
      })
    }

    const nicknamereset = () => {
      nicknameChk = false;
    }

    const emailreset = () => {
      emailChk = false;
    }

    const isValidEmail = (email) => {
      axios.get("/api/register/emailchk/" + email)
      .then((res) => {
        if(!res.data){
          emailChk = true;
          alert("사용 가능한 이메일 입니다.")
        }else {
          emailChk = false;
          alert("이미 존재하는 이메일 입니다.")
        }
      })
    }

    
    
    const submit = () => {
      if(!nicknameChk) {
        alert("닉네임 중복 체크 해주세요.");
        return
      }

      if(!emailChk) {
        alert("이메일 중복 체크 해주세요.");
        return
      }
      if(state.form.email == "" || !state.form.email){
        alert("이메일을 입력해 주세요");
        return
      }
      if(state.form.nickname == "" || !state.form.nickname){
        alert("닉네임을 입력해 주세요");
        return
      }
      if(state.form.name == "" || !state.form.name){
        alert("이름을 입력해 주세요");
        return
      }
      if(state.form.password == "" || !state.form.password){
        alert("비밀번호를 입력해 주세요");
        return
      }

      if(state.form.password != state.form.passwordConfirm) {
        alert("비밀번호와 비밀번호 확인이 값이 다릅니다.");
        return 
      }

      axios.post("/api/register",state.form)
      .then((res) => {
        console.log(res.data);
        alert("회원가입이 완료 되었습니다.");
        router.push({path:"/login"});
      }).catch((err)=>{
        window.alert(err.message);
      })
    }

    return {state,submit,isValidEmail,isValidNickname,emailChk,nicknameChk,nicknamereset,emailreset}
    
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
