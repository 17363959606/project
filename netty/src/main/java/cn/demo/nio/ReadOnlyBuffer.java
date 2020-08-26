package cn.demo.nio;

import java.nio.ByteBuffer;

/**
 * 只读buffer
 */

public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1025);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 4);
        byteBuffer.flip();
        ByteBuffer byteBuffer1 = byteBuffer.asReadOnlyBuffer();
        while (byteBuffer1.hasRemaining()) {
            System.out.println(byteBuffer1.get());
        }
        //      asReadOnlyBuffer为只读buffer，进入put会出现如下异常：  ReadOnlyBufferException
        //byteBuffer1.put((byte) 5);

    }
}
