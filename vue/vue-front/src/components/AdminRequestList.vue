<template>
  <div>
    <div>
      <table border="1" style="width:1000px">
        <th style="width:10%">no.</th>
        <th style="width:20%">유저ID</th>
        <th>문의제목</th>
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
      <table border="1">
        <tr v-for="item in list" :id="'contentsDetail_'+item.seq" style="display: none">
          <table border="1">
            <tr>
              <td>유저ID</td>
              <td style="width:800px">{{ item.userId }}</td>
            </tr>
            <tr>
              <td>제목</td>
              <td>{{ item.title }}</td>
            </tr>
            <tr>
              <td>내용</td>
              <td v-html="item.contents.replaceAll('\n','<br>')"></td>
            </tr>
            <tr v-if="!!item.resUserId">
              <td>
                담당자
              </td>
              <td>
                {{item.resUserId}}
              </td>
            </tr>
            <tr v-else>
              <td colspan="2"><button @click="confirm(item.seq)">담당자로 지정</button></td>
            </tr>
            <tr v-if="!!item.resUserId">
              <td>
                답변
              </td>
              <td>
                <textarea cols="50" rows="50" :id="'resContents_'+item.seq" v-html="item.resContents"></textarea>
              </td>
            </tr>
            <tr v-if="!!item.resUserId">
              <td colspan="2">
                <button @click="responseSave(item.seq)">저장하기</button>
              </td>
            </tr>
          </table>
        </tr>

      </table>

    </div>
  </div>
</template>

<script>
import store from "../store";

export default {
  name: 'AdminRequestList',
  data () {
    return {
      msg: 'Admin문의하기목록',
      list: [],
    }
  },
  methods: {
    detail(idx){
      for(var i=0;i<document.querySelectorAll('[id^="contentsDetail_"]').length;i++){
        document.querySelectorAll('[id^="contentsDetail_"]')[i].style.display='none';
      }
      document.getElementById('contentsDetail_'+idx).style.display='block';
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
    },
    confirm(idx){
      if(store.getters.isLoggedIn && typeof store.getters.getUserName !== 'undefined' && store.getters.getUserName !=='') {
        this.$axios
          .put("/api/confirmRequest",{
            resUserId : store.getters.getUserName,
            seq: idx
          })
          .then((res) => {
            if (res.data.result) {
              if(res.data.data){
                alert('이미 담당자로 '+res.data.data+'가 지정되어 있습니다.');
              }else{
                alert('담당자로 지정 되었습니다.');
                this.getList();
              }
            } else {
              alert('실패하였습니다.('+res.data.data+')');
            }
          }).catch((error) => {
            console.log(error);
          }).finally(() => {
          });
      }else{
        this.$router.push('/login')
      }
    },

    responseSave(idx){
      this.$axios
        .put("/api/responseSave",{
          resContents : document.getElementById('resContents_'+idx).value,
          seq: idx
        })
        .then((res) => {
          if (res.data.result) {
              alert('답변이 저장되었습니다.');
              this.getList();
          } else {
            alert('실패하였습니다.('+res.data.data+')');
          }
        }).catch((error) => {
        console.log(error);
      }).finally(() => {
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
