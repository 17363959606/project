package cn.demo.netty.noprotocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //使用netty自带的的解码器
        pipeline.addLast(new StringDecoder());
        //自定义出站编码器
//        pipeline.addLast(new MessageEncoder());
        //编写自定义的业务处理器
        pipeline.addLast(new MyServerHandler());
    }
}
