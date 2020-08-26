package cn.demo.zerocopy;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(7001));
        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true) {
            int readCount = 0;
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                } catch (IOException e) {
//                    e.printStackTrace();
                    break;
                }
                byteBuffer.rewind();//倒带 position = 0,mark作废
            }
        }
    }
}
