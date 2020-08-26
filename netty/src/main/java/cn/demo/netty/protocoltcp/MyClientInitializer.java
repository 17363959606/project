package cn.demo.netty.protocoltcp;

import cn.demo.netty.inboundandoutbound.ByteToLongDecoder;
import cn.demo.netty.inboundandoutbound.ClientHandler;
import cn.demo.netty.inboundandoutbound.MessageLongToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // socket 写信息到 channel 称为：入站； 入站对应->Inbound->解码 DeCodec
        // channel 写信息到 socket 称为：出站； 出站对应->outbound->编码 EnCodec
        ChannelPipeline pipeline = ch.pipeline();
        //自定义出站编码器
        pipeline.addLast(new MessageEncoder());
        //自定义入站解码器
        pipeline.addLast(new MessageDecoder());
        //自定义业务处理器
        pipeline.addLast(new MyClientHandler());
    }
}
