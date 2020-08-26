package cn.demo.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("d:\\1.png");
        FileChannel fileChannelInput = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\2.png");
        FileChannel fileChannelOutput = fileOutputStream.getChannel();
        
        fileChannelOutput.transferFrom(fileChannelInput, 0, fileChannelInput.size());

        fileInputStream.close();
        fileOutputStream.close();
    }
}
