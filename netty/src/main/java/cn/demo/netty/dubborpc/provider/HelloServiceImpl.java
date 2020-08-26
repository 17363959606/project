package cn.demo.netty.dubborpc.provider;

import cn.demo.netty.dubborpc.publicinterface.HelloService;

/**
 * 提供者实现 公共接口类
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        if (msg != null) {
            return "你好客户端,服务器收到信息：【 " + msg + "】";
        } else {
            return "你好客户端,服务器收到信息: ";
        }

    }
}
