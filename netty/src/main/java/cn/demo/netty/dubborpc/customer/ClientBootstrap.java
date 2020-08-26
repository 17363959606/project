package cn.demo.netty.dubborpc.customer;

import cn.demo.netty.dubborpc.netty.NettyClient;
import cn.demo.netty.dubborpc.publicinterface.HelloService;

/**
 * 消费端启动类
 */
public class ClientBootstrap {
    public static final String providerName = "#HelleoService#hello#";

    public static void main(String[] args) {
        NettyClient consumer = new NettyClient();

        //创建一个代理对象
        HelloService service = (HelloService) consumer.getBean(HelloService.class, providerName);

        String s = service.hello("你好 dubbo ~");
        System.out.println("customer get result :" + s);
    }
}
