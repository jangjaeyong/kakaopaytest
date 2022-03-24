import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Request from '@/components/Request'
import RequestList from '@/components/RequestList'
import RequestDetail from '@/components/RequestDetail'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'RequestList',
      component: RequestList
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
      path: '/hello',
      name: 'HelloWorld',
      component: HelloWorld
    }
  ]
})
