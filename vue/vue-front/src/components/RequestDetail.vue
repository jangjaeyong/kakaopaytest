<template>
  <div>
    <div>
      detail
      <label for="ID">ID:{{idx}}</label><br>
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
      idx: this.$route.query.idx
    }
  },
  methods: {

  },
  created() {
    alert();
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
