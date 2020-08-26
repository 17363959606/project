package cn.demo.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 1. MappedByteBuffer 可以文件直接在内存（堆外内存）修改，操作系统不需要拷贝一次
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws Exception {
        String fileStr = "d:\\file01.txt";
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileStr, "rw");
        //获取对应的通道
        FileChannel fileChannel = randomAccessFile.getChannel();
        /**
         * 参数1：FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数2： 0 可以直接修改的起始位置
         * 参数3： 5 是映射到内存的大小（不是索引位置），即将文档中的多少个字节映射到内存中
         * 可以直接修改的范围就是0-5
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
//        mappedByteBuffer.put(5, (byte) '9'); 改成5会报错：IndexOutOfBoundsException
        mappedByteBuffer.put(4, (byte) '9');
        randomAccessFile.close();
    }
}
