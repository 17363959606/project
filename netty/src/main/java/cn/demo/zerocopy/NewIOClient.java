package cn.demo.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 7001));
        FileChannel fileChannel = new FileInputStream("d:\\1.png").getChannel();
        //String filename = "d:\\1.png";
        long startTime = System.currentTimeMillis();
        //在linux下一个transferTo方法就可以完成
        //在windows中一次最多只能传8M，超过8M需要分段处理
        //transferTo底层实现了0拷贝
        long count = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数：" + count + ",耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }
}
