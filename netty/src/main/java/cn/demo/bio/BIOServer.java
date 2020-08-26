package cn.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用BIO创建一个服务端监听端口6666的连接
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        //线程池机制
        //思路
        //1.创建一个线程池
        //2.如果有客户端连接就创建一个线程与之通讯（单独写一个方法）

        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true) {
            System.out.println("线程信息id：" + Thread.currentThread().getId() + "名字：" + Thread.currentThread().getName());
            //监听等待客户端
            System.out.println("等待连接....");
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            //创建一个线程与之通讯
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //重写可以和客户端通讯
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //编写一个handler方法和客户端通讯
    public static void handler(Socket socket) throws IOException {
        System.out.println("线程信息id：" + Thread.currentThread().getId() + "名字：" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        //通过socket获取输入流
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        //循环读取客户端发送的数据
        while (true) {
            System.out.println("线程信息id：" + Thread.currentThread().getId() + "名字：" + Thread.currentThread().getName());
            System.out.println("read....");
            int read = inputStream.read(bytes);
            if (read != -1) {
                //输入客户端发送的数据
                System.out.println(new String(bytes, 0, read));
            } else {
                break;
            }
        }
        inputStream.close();
        outputStream.close();
    }
}
