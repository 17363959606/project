package cn.demo.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        //new String (msg.getContent(), CharsetUtil.UTF_8)
        System.out.println("--------服务器拿取数据:" + new String(msg.getContent(), 0, msg.getLen()) + "拿取次数：" + count++);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        String content = "收到了，亲爱的";
        byte[] contentBytes = content.getBytes(CharsetUtil.UTF_8);
        int len = content.getBytes(CharsetUtil.UTF_8).length;
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len);
        messageProtocol.setContent(contentBytes);
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(messageProtocol);
            System.out.println("服务器发送信息： " + content + "发送次数： " + count++);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
}
