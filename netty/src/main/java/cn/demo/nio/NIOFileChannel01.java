package cn.demo.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用NIOFileChannel将数据写入到指定的文档中
 */
public class NIOFileChannel01 {

    public static void main(String[] args) throws IOException {
        String str = "hello,NIOFileChannel";
        //创建文件输出流  (创建一个输出流-》channel)
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");
        //获取文件流的管道
        //通过fileOutputStream获取对应的fileChannel
        //这个fileChannel真实得了类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建byteBuffer缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //往缓冲区中写入数据
        byteBuffer.put(str.getBytes());
        //进行数据的读写切换
        byteBuffer.flip();
        //将byteBuffer数据写入到FileChannel中
        fileChannel.write(byteBuffer);
        //关闭流
        fileOutputStream.close();
    }
}
