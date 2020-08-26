package cn.demo.netty.protocoltcp;

import cn.demo.netty.inboundandoutbound.ByteToLongDecoder;
import cn.demo.netty.inboundandoutbound.MessageLongToByteEncoder;
import cn.demo.netty.inboundandoutbound.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //编写自定义的解码器
        pipeline.addLast(new MessageDecoder());
        //自定义出站编码器
        pipeline.addLast(new MessageEncoder());
        //编写自定义的业务处理器
        pipeline.addLast(new MyServerHandler());
    }
}
