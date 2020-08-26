/**
 * 生产环境配置
 */
const uglifyjs = require('uglifyjs-webpack-plugin')
const bastConfig = require('./base.config')

module.exports = uglifyjs(bastConfig, {
    plugin:[
        new uglifyjs()
    ]
    
})