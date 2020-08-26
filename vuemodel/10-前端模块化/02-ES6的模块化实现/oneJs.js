let name = "小红"
let age = 16
let falg = false
function oneFun(){
    console.log("oneJs:"+ name)
}
oneFun()
// 导出模块数据，其他js地方需要引用使用impory导入即可
export{
    name,age,falg,oneFun
}
// 导出类
export class funHandel{
    run(name){
        console.log("fun on run" + name)
    }
}

//导出default类、或变量  一个模块里面只能有一个default,用default导出的元素在导入时
//命名规则随便命名，不需要和导出的名称一致

// export default  city = '北京'