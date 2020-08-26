package cn.demo.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * 消费者netty处理
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private ChannelHandlerContext context; //channel上下文
    private String param;//发送的内容参数
    private String result;//返回的结果

    //连接时第一个会执行且只会执行一次 第一步执行
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
    }

    //第四步执行
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        //唤醒等待的线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    //被代理对象调用，发送数据给服务器->wait->等到被唤醒(channelRead.notify)->接受结果
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        //等待，等到channelRead获取到服务器接口再唤醒
        wait();
        return result;
    }

    //设置参数 第二步执行
    public void setParam(String param) {
        this.param = param;
    }
}
