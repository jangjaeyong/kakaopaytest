<template>
  <form @submit.prevent="submitForm">
    <div>
      <router-link to="/">목록으로 이동</router-link>
    </div>
    <div>
      <label for="ID">ID:</label><br>
      <input id="id" type="text" v-model="id" required/>
    </div>
    <div>
      <label for="pw">Pw:</label><br>
      <input id="pw" type="password" v-model="pw" required/>
    </div>
    <div>
      <label for="title">문의 제목:</label><br>
      <input id="title" type="text" v-model="title" required/>
    </div>
    <div>
      <label for="contents">문의 내용:</label><br>
      <textarea id="contents" v-model="contents" required></textarea>
    </div>
    <button :class="[id ? activeClass : '']" type="submit">문의하기</button>

  </form>

</template>

<script>
export default {
  name: 'Request',
  data () {
    return {
      msg: '문의하기',
      id: '',
      pw: '',
      title: '',
      contents: '',
      activeClass: 'active'
    }
  },
  methods: {
    submitForm() {
        this.$axios
        .post("/api/saveData",{
          userId: this.id,
          userPw : this.pw,
          title : this.title,
          contents : this.contents
        })
        .then((res) => {
            if(res.data.result){
              alert('문의 되었습니다.');
            }else{
              alert('문의에 실패하였습니다.('+res.data.data+')');
            }
        }).catch((error) => { console.log(error);
        }).finally(() =>{
            this.id= '';
            this.pw= '';
            this.title= '';
            this.contents= '';
        });

      }
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
