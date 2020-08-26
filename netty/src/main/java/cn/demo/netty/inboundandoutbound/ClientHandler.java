package cn.demo.netty.inboundandoutbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("客户端读取信息：" + msg);
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端发送数据---");
        ctx.writeAndFlush(1234L);
        /*
             abcdabcdabcdabcd  是一个16个字节
             它的前一个处理器是：MessageLongToByteEncoder
             MessageLongToByteEncoder 的父类是：MessageToByteEncoder

             MessageToByteEncoder中有个write中的acceptOutboundMessage方法
             用来判断发送的类型是否属于MessageLongToByteEncoder中的Long类型
             是则走Encoder处理器,不是则直接输出
             因此我们Encoder的时候要注意，传入的数据类型和要处理的数据类型要一至
         */
//        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }


}
