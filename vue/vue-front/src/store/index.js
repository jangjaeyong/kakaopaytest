import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loginSuccess: false,
    loginError: false,
    userName: null
  },
  mutations: {
    loginSuccess(state, {userName, password}) {
      state.loginSuccess = true;
      state.userName = userName
      state.password = password
    },
    loginError(state, {userName, password}) {
      state.loginError = true;
      state.userName = userName
      state.userName = password
    }
  },
  actions: {
    async login({commit}, {user, password}) {
      try {
        const result = await axios.get('/api/login', {
          auth: {
            username: user,
            password: password
          }
        });
        if (result.status === 200) {
          commit('loginSuccess', {
            userName: user,
            userPass: password
          });
        }
      } catch (err) {
        commit('loginError', {
          userName: user
        });
        throw new Error(err)
      }
    }
  },
  getters: {
    isLoggedIn: state => state.loginSuccess,
    hasLoginErrored: state => state.loginError,
    getUserName: state => state.userName,
    getUserPass: state => state.userPass
  },
  modules: {
  }
})
