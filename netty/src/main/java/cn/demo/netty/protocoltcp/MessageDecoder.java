package cn.demo.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    /**
     * @param ctx 上下文
     * @param in  入站的数据
     * @param out 解码数据，将传递给下一个Handler处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("解码器执行---");
        int len = in.readInt();
        byte[] content = new byte[len];
        in.readBytes(content);
        //解析收到的协议内容
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(len);
        messageProtocol.setContent(content);
        //将数据交给下一个handler处理
        out.add(messageProtocol);
    }
}
