package cn.demo.netty.dubborpc.provider;

import cn.demo.netty.dubborpc.netty.NettyServer;

/**
 * 服务器启动类
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        //启动一个服务提供者
        NettyServer.startServer("127.0.0.1", 6666);
    }

}
