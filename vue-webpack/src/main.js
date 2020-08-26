import * as mathUtils from './js/mathUtils.js'
import * as infoJs from './js/info'
import css from './css/normal.css'
// 英

infoJs.infoFun()
//commonJs方式导入
// const {name,age} = require('./mathUtils')
// require('./css/normal')

/**
 * 使用Vue进行开发  方式一:
 *
// import Vue from 'vue'
// new Vue({
//     el : '#app',
//     data:{
//         message:'Hello Webpack'
//     },
//     template: `
//     <div>
//         <h2>{{message}}</h2>
//         <button @click="btn">按钮</button>
//     </div>
//     `,
//     methods:{
//         btn(){
            
//         }
//     }
// })

/**
 * 使用Vue进行开发方式二：
 */
// import Vue from 'vue'
// const cpn = {
//         template: `
//     <div>
//         <h2>{{message}}</h2>
//         <button @click="btn">按钮</button>
//     </div>`,
//     data(){
//         return {message : '方式二'}
//     },
//     methods:{
//         btn(){

//         }
//     }
// }
// new Vue({
//     el:'#app',
//     components:{
//         cpn
//     },
//     template: '<cpn/>'
// })

/**
 * 使用Vue进行开发 方式三：
 * 查看vue文件下的App.vue
 */
import Vue from 'vue'
import App from './vue/App.vue'
new Vue({
    el:'#app',
    components:{
        App
    },
    template:'<App/>'
})