import {falg,oneFun,funHandel,defaultFun} from "./oneJs.js"
let name = "小明"
let age = 17
function fun(){
    console.log(name)
}
oneFun()
fun()
if (!falg){
    console.log("oneJs modules时oneJs里面的属性无法调用")
}
let funs = new funHandel()
funs.run(name)
// import citiss from "./oneJs.js"
// console.log(citiss)

// 5.统一全部导入
// * 表示所有数据， as importModel 表示别名，防止和命名重复
//调用：importModel.name
// import * as importModel from "./oneJs"