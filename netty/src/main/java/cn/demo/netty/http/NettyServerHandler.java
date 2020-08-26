package cn.demo.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * SimpleChannelInboundHandler 是 ChannelInboundHandlerAdapter 的子类
 * HttpObject 客户端和服务器端相互通讯的数据被封装成 HttpObject
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    //读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("_____________");
        //判断msg是不是httpRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println("msg 类型=" + msg.getClass());
            System.out.println("客户端地址：" + ctx.channel().remoteAddress());

            //过滤掉指定的文件
            HttpRequest httpRequest = (HttpRequest) msg;
            if ("/favicon.ico".equals(httpRequest.uri())) {
                System.out.println("接收到favicon.ico不做处理");
                return;
            }
            //回复信息给客户端 http协议
            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务端", CharsetUtil.UTF_8);
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            ctx.writeAndFlush(defaultFullHttpResponse);
        }
    }
}
