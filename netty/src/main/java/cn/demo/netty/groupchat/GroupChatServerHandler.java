package cn.demo.netty.groupchat;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    //因为存在多个客户端端，根据业务需求这里创建一个channel组管理所有的channel
    //GlobalEventExecutor.INSTANCE是一个全局的事件执行器，是一个单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    //handlerAdded 表示连接建立，一旦被建立第一个被执行
    //将当前的channel加入到channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // NOOP
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他在线的客户端
        /*
          该方法会将channelGroup中所有的channel遍历并发送，我们不需要自己遍历
         */
        channelGroup.writeAndFlush("[客户]" + channel.remoteAddress() + "加入聊天室\n");
        channelGroup.add(channel);
    }

    //表示channel处于活动状态，提示xx上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线了");

    }

    //表示channel处于非活动状态，提示xx离线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "离线了");
    }

    //断开连接
    //handlerRemoved执行后会自己从channelGroup删除掉相关channel
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户]" + channel.remoteAddress() + "离开了聊天室\n");
    }

    //转发处理
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            //不是当前的channel,转发信息
            if (ch != channel) {
                ch.writeAndFlush("[客户]" + channel.remoteAddress() + "发送信息：" + msg + "\n");
            } else {
                //如果是自己
            }
        });
    }

    //处理异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
}
