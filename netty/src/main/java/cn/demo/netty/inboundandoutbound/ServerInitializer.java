package cn.demo.netty.inboundandoutbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //编写自定义的解码器
        pipeline.addLast(new ByteToLongDecoder());
        //自定义出站编码器
        pipeline.addLast(new MessageLongToByteEncoder());
        //编写自定义的业务处理器
        pipeline.addLast(new ServerHandler());
    }
}
