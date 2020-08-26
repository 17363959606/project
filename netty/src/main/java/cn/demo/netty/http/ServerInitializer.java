package cn.demo.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 用户自定义初始化类
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //得到管道
        ChannelPipeline pipeline = ch.pipeline();
        //向管道加入处理器
        //加入一个netty提供的HttpServerCodec codec =>[coder - decoder]
        //HttpServerCodec 说明：netty提供的基于http的编解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        //再新增一个自定义的处理器
        pipeline.addLast(new NettyServerHandler());
    }
}
