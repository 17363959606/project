package cn.demo.netty.hearbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class HearBeatServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
        switch (idleStateEvent.state()) {
            case READER_IDLE:
                System.out.println("读闲置");
                break;
            case WRITER_IDLE:
                System.out.println("写闲置");
                break;
            case ALL_IDLE:
                System.out.println("读写闲置");
                break;
        }
//        ctx.channel().close();

    }
}
