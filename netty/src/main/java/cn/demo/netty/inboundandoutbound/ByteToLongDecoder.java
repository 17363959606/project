package cn.demo.netty.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ByteToLongDecoder extends ByteToMessageDecoder {

    /**
     *
     * @param ctx  上下文
     * @param in  入站的数据
     * @param out  解码数据，将传递给下一个Handler处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("解码----------------");
        //1个long类型为8字节
        if (in.readableBytes() >= 8) {//判断可读字节
            out.add(in.readLong());
        }

    }
}
