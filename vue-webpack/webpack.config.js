//导入path这个包，没有就npm安装
const path = require('path')
//导入html模板插件
const HtmlWebpackPlugin = require('html-webpack-plugin')
//js压缩插件
const uglifyjs = require('uglifyjs-webpack-plugin')
const webpack = require('webpack')

module.exports = {
    //入口
    'entry':'./src/main.js',
    // 出口
    output:{
        //路径  动态的指定编译后文件路径，并指定文件存放的地址为：dist
        path: path.resolve(__dirname,'dist'),
        //文件名称
        filename:'bundle.js',
        //编译后的文件里面静态资源的地址前缀，当html文件也被编译打包入dist后去掉。
        // publicPath:'dist/'
    },
    module:{
        //规则
        rules:[
            {
                //识别样式
                test: /\.css$/,
                //使用多个loader是，是从右向左读
                use: ['style-loader','css-loader']
            },
            {
                //识别加载图片
                test: /\.(png|jpg|gif)$/,
                //使用多个loader是，是从右向左读
                use: [
                    {
                        loader:'url-loader',
                        options: {
                            // 当加载的图片，小于8192，图片会以base64形式展示
                            //当加载的图片，大于limit时需要用file-loader模块进行加载
                            limit: 8192,
                            // name: '[path][name][hash].[ext]'  指定图片文件生成的名字格式
                            name: 'img/[name][hash].[ext]'
                        }
                    }
                ]
            },
            {
                //将es6语法转换为es5语法（bundle.js）
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                use: {
                    loader: 'babel-loader',
                    options:{
                        presets: ['es2015']
                    }
                }
            },
            {
                test:/\.vue$/,
                use:['vue-loader']
            }
        ]
    },
    resolve:{
        //指定vue路径，解决使用vue模板时遇到的：runtime-only错误
        alias:{
            'vue$':'vue/dist/vue.esm.js'
        },
        //配置可以省略的后缀名
        extensions:['.js','.css','.vue']
    },
    plugins:[
        //根据'./index.html'生成文件于的dist文件下
         new HtmlWebpackPlugin({
             template:'./index.html'
         }),
         new uglifyjs()
        // new webpack.BannerPlugin('最终版权归Thw所有')
    ],
    //npm install --save-dev webpack-dev-server@2.9.3 
    // 搭建一个本地服务,contentBase：指向本地，inline:true 实时刷新页面
    devServer:{
        contentBase: './dist',
        inline: true
    }
}