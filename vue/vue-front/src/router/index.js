import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Request from '@/components/Request'
import RequestList from '@/components/RequestList'
import AdminRequestList from '@/components/AdminRequestList'
import RequestDetail from '@/components/RequestDetail'
import Login from '@/components/Login'

import store from '../store'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'RequestList',
    component: RequestList
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/request',
    name: 'Request',
    component: Request
  },
  {
    path: '/requestDetail',
    name: 'RequestDetail',
    component: RequestDetail
  },
  {
    path: '/admin/requestList',
    name: 'AdminRequestList',
    component: AdminRequestList,
    meta: {
      requiresAuth: true
    }
  },

  {
    path: '/hello',
    name: 'HelloWorld',
    component: HelloWorld
  },
  {path: "*", redirect: '/'}
];

const router = new Router({
  mode: 'history',
  routes
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.getters.isLoggedIn) {
      next({
        name: 'Login'
      })
    } else {
      next();
    }
  } else {
    next();
  }
});

export default router
