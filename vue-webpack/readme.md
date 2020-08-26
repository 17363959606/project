详情请看：webpack官方 https://www.webpackjs.com/
package.json:
devDependencies 为开发环境的依赖，生成命令：npm install webpack@3.6.0 --save-dev
    css-loader: Css样式装置 生成命令：npm install --save-dev css-loader
scripts:
    build: "webpack"  构建项目
    "dev":"webpack-dev-server --open"   启动本地服务,并默认打开页面，实时刷新配置请看：webpack.config.js 
-------------
目录：
dist:
    打包后运行的文件
