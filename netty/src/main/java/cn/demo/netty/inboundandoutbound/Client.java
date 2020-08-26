package cn.demo.netty.inboundandoutbound;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    public static void main(String[] args) throws Exception {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.channel(NioSocketChannel.class)
                    .group(eventExecutors)
                    .handler(new ClientInitializer());
            ChannelFuture cf = bootstrap.connect("127.0.0.1",6666).sync();
            cf.channel().closeFuture().sync();

        } finally {
            eventExecutors.shutdownGracefully();
        }

    }

}
