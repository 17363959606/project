package cn.demo.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("客户端读取信息：" + new String(content, 0, len) + "读取次数： " + count++);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    /*
        使用自定义协议解决粘包拆包问题
     */
        String content = "你好啊，亲爱的";
        byte[] bytes = content.getBytes(CharsetUtil.UTF_8);
        int len = content.getBytes(CharsetUtil.UTF_8).length;
        //定义发送协议内容
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len);
        messageProtocol.setContent(bytes);
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(messageProtocol);
            System.out.println("客户端发送信息：" + content + "发送次数：" + count++);
        }

    /*
        不使用自定义协议 可能会出现 粘包拆包问题
     */
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(messageProtocol);
            System.out.println("客户端发送信息：" + content + "发送次数：" + count++);
        }
    }

}
