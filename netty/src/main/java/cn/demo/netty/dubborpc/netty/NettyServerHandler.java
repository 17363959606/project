package cn.demo.netty.dubborpc.netty;

import cn.demo.netty.dubborpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //定义一个简单的协议来判断是否调用本地函数类
        //:如以：#HelleoService#hello#xxx(#HelleoService#hello#为协议内容，xxx为客户端传过来的信息)
        String content = msg.toString();
        if (content.startsWith("#HelleoService#hello#")) {
            content = content.replace("#HelleoService#hello#", "");
            ctx.writeAndFlush(new HelloServiceImpl().hello(content));
        } else {
            ctx.writeAndFlush(msg);
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
