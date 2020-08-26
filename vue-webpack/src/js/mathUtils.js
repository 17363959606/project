let name = 'Tom'
let age = 18
function fun0(){
    console.log("My name is " + name)
}
fun0()
// es6的导出
export {name,age,fun0}
// commonJs 方式导出
// module.exports = {name,age,fun0}
