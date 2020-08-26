package cn.demo.netty.noprotocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // socket 写信息到 channel 称为：入站； 入站对应->Inbound->解码 DeCodec
        // channel 写信息到 socket 称为：出站； 出站对应->outbound->编码 EnCodec
        ChannelPipeline pipeline = ch.pipeline();
        //使用netty自带的编码处理器
        pipeline.addLast(new StringEncoder());
        //自定义入站解码器
//        pipeline.addLast(new MessageDecoder());
        //自定义业务处理器
        pipeline.addLast(new MyClientHandler());
    }
}
