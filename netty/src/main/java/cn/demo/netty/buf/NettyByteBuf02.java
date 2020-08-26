package cn.demo.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world!", CharsetUtil.UTF_8);
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            System.out.println(new String(array, CharsetUtil.UTF_8));

            System.out.println(byteBuf.readerIndex());//返回当前缓存区读取位置的索引 0
            System.out.println(byteBuf.writerIndex());//返回当前缓存区输出位置的索引 12
            System.out.println(byteBuf.capacity());//返回当前缓存区容量 36
            System.out.println(byteBuf.readableBytes());//返回当前缓冲区可读字节 等于：writerIndex - readerIndex 12
            System.out.println(byteBuf.arrayOffset());//返回的备份字节数组中第一个字节的偏移 0

            System.out.println((char) byteBuf.readByte());// 获取当前{@code readerIndex}处的一个字节并增加这个缓冲区中{@code 1}的{@code readerIndex}
            System.out.println(byteBuf.readBytes(0));//这个是按照索引取值不会影响readerIndex
            //获取指定大小的字符，从索引0开始，取四位
            System.out.println(byteBuf.getCharSequence(0, 4, CharsetUtil.UTF_8));
            System.out.println(byteBuf.readerIndex());//返回当前缓存区读取位置的索引 1

        }


    }
}
