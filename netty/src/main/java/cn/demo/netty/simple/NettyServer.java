package cn.demo.netty.simple;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 简单版netty服务端
 */
public class NettyServer {
    public static void main(String[] args) throws Exception {
        /*
         *创建BossGroup 和 workerGroup
         * 说明：创建两个线程组bossGroup和workerGroup
         * bossGroup只是处理连接请求，真正的和客户端业务处理会交给workerGroup完成
         * 两个都是无限循环的
         * NioEventLoopGroup 里面的线程数默认为：cpu和数*2，当然也可以执行设置：new NioEventLoopGroup(1)
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建服务器端的启动对象，配置参数
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)//设置两个线程组
                    .channel(NioServerSocketChannel.class)//使用NioServerSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true)//设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建一个通道测试对象（匿名对象）
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("客户socketChannel hashCode= " + ch.hashCode());
                            //可以使用一个集合管理SocketChannel，再推送消息时可以将业务加入到对应的各个channel
                            //对应的NioEvenLoop的taskQueue或者scheduleTaskQueue
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println(".....服务器 is ready ....");
            //绑定一个端口并且同步生成一个ChannelFuture
            //启动服务
            ChannelFuture channelFuture = bootstrap.bind(6666).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅的关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }
}
