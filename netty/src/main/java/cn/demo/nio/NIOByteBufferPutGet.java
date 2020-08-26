package cn.demo.nio;

import java.nio.ByteBuffer;

/**
 * 类型化put和get数据
 * put和get顺序一定要对应否则可能会出现：BufferUnderflowException异常
 */

public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putInt(1);
        byteBuffer.putChar('2');
        byteBuffer.putShort((short) 3);
        byteBuffer.putLong(23L);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getLong());
//        BufferUnderflowException异常
//        System.out.println(byteBuffer.getLong());

    }
}
