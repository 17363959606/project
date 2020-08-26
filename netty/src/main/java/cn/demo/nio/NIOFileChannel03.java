package cn.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用一个Buffer完成文件的读取复制
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("d:\\file01.txt");
        FileChannel fileChannelInput = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file03.txt");
        FileChannel fileChannelOutput = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        //循环读取
        while (true) {
            //clear一定需要写上否则会死循环
            byteBuffer.clear();
            int read = fileChannelInput.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileChannelOutput.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();

    }
}
