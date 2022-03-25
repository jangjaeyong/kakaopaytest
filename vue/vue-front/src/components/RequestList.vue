<template>
  <div>
    <div>
      <router-link to="/request">문의하기</router-link>
    </div>
    <hr>
    <div>
      <table border="1">
        <th style="width:10%">no.</th>
        <th style="width:20%">유저ID</th>
        <th>문의글</th>
        <th style="width:20%">문의일시</th>
        <th style="width:10%">담당자</th>
        <th style="width:20%">답변일시</th>
        <th style="width:10%">&nbsp;</th>
        <tr v-for="item in list">
          <td>{{ item.seq }}</td>
          <td>{{ item.userId }}</td>
          <td>{{ item.title }}</td>
          <td>{{ item.regYmdt }}</td>
          <td>{{ item.resUserId }}</td>
          <td>{{ item.resYmdt }}</td>
          <td><button @click="detail(item.seq)">글확인</button></td>
        </tr>
      </table>

    </div>
  </div>
</template>

<script>
export default {
  name: 'RequestList',
  data () {
    return {
      msg: '문의하기목록',
      list: []
    }
  },
  methods: {
    detail(idx){
      this.$router.push({name: 'RequestDetail', query: {idx: idx}})
    },
    getList(){
      this.$axios
        .get("/api/getData")
        .then((res) => {
          if(res.data.result){
            this.list= res.data.data;
          }else{
            alert('조회에 실패하였습니다.('+res.data.data+')');
          }
        }).catch((error) => { console.log(error);
      }).finally(() =>{
      });
    }
  },
  created() {
    this.getList();
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
