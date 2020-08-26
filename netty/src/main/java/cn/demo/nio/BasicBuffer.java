package cn.demo.nio;

import java.nio.IntBuffer;

/**
 * Buffer的基本使用
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //举例说明Buffer的使用
        //创建一个Buffer大小为5，即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(2);
        intBuffer.put(4);
        intBuffer.put(6);
        intBuffer.put(8);
        intBuffer.put(10);
        //如何从buffer中读取数据
        //将buffer转换读写切换
        intBuffer.flip();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
