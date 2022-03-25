<template>
  <div>
    <div>
      <router-link to="/">목록으로 이동</router-link>
    </div>
    <div v-if="!detailAuth">
      글읽기 권한 인증
      <form @submit.prevent="submitForm">
        <div>
          <label for="authId">ID:</label><br>
          <input id="authId" type="text" v-model="authId" required/>
        </div>
        <div>
          <label for="authPw">Pw:</label><br>
          <input id="authPw" type="password" v-model="authPw" required/>
        </div>
        <button type="submit">인증확인</button>
      </form>
    </div>
    <div v-else>
      <div>
        ID:{{id}}
      </div>
      <div>
        문의 제목:{{title}}
      </div>
      <div>
        문의 내용: <br>
        <div style="border: 1px solid">
          <span v-html="contents"></span>
        </div>
      </div>
      <div>
        답변 내용: <br>
        <div style="border: 1px solid">
          <span v-html="resContents"></span>
        </div>
      </div>
      <div>
        답변일시:{{resYmdt}}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RequestDetail',
  data () {
    return {
      msg: '문의하기상세',
      id: '',
      pw: '',
      title: '',
      contents: '',
      resContents: '',
      resYmdt: '',
      idx: this.$route.query.idx,
      detailAuth:false,
      authId: '',
      authPw: ''
    }
  },
  methods: {
    getDetail(idx){
      this.$axios
        .get("/api/getDataDetail",{
          params: {
            seq: idx
          }
        })
        .then((res) => {
          if(res.data.result){
            this.id= res.data.data.userId;
            this.title= res.data.data.title;
            this.contents= res.data.data.contents.replace(/\n/g,'<br/>');
            this.resContents= res.data.data.resContents.replace(/\n/g,'<br/>');
            this.resYmdt= res.data.data.resYmdt;
          }else{
            alert('조회에 실패하였습니다.('+res.data.data+')');
          }
        }).catch((error) => { console.log(error);
      }).finally(() =>{
      });
    },

    submitForm(){
      this.$axios
        .post("/api/chkAuthData",{
          userId: this.authId,
          userPw : this.authPw,
          seq : this.idx
        })
        .then((res) => {
          if(res.data.result){
            this.detailAuth=true;
          }else{
            alert('인증에 실패하였습니다.');
          }
        }).catch((error) => { console.log(error);
      }).finally(() =>{
        this.id= '';
        this.pw= '';
      });
    }
  },
  created() {
    this.getDetail(this.idx);
  }
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
$primary: #5968d7;

form {
  width: 500px;
  padding: 10px 40px;
  label {
    text-transform: uppercase;
    font-size: 13px;
    letter-spacing: 0.03em;
    font-weight: bold;
  }
  input, textarea {
    border: 1px solid #ccc;
    color: #333;
    width: calc(100% - 30px);
  }
  input, textarea, button {
    border-radius: 4px;
    padding: 8px 15px;
    font-family: 'Work Sans', sans-serif;
    font-weight: 300;
  }
  div {
    margin: 20px 0;
}
}

button {
  color: white;
  border: none;
  width: calc(100% - 30px);
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  background: #ccc;
  cursor: pointer;
  box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.3);
  transition: 0.25s all ease;
&:hover {
   transform: translateY(2px);
 }
}

.active {
  background: $primary;
}

pre-content {
  width: 500px;
}
</style>
