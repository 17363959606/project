package cn.demo.netty.noprotocoltcp;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("客户端读取信息：" + msg + "读取次数： " + count++);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    /*
        不使用自定义协议 可能会出现 粘包拆包问题
     */
        String content = "你好啊";
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(content);
            System.out.println("客户端发送信息：" + content + "发送次数：" + count++);
        }
    }

}
