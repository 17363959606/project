package cn.demo.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * http服务
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup serverBossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(serverBossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerInitializer());
            ChannelFuture sync = serverBootstrap.bind(6668).sync();
            System.out.println("服务器启动成功...");
            sync.channel().closeFuture().sync();

        } finally {
            serverBossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
