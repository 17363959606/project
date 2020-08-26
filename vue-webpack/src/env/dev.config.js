/**
 * 本地环境配置
 */
const webapckDevServer = require('webpack-dev-server')
const bastConfig = require('./base.config')
module.exports = webapckDevServer(bastConfig, {
    devServer:{
        contentBase: './dist',
        inline: true
    }
})