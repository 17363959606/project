package cn.demo.groupchat;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 编写聊天室客户端
 */
public class GroupChatClient {
    //定义常用参数
    private Selector selector;
    private SocketChannel socketChannel;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6667;
    private String userName;

    //构造函数初始化产生
    public GroupChatClient() throws Exception {
        //打开客户端连接到指定的ip和端口
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        //设置为阻塞模式
        socketChannel.configureBlocking(false);
        //打开selector
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        userName = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println("客户端 :" + userName + "is ok..");
    }

    //发送信息至服务端
    private void sendMsg(String msg) throws IOException {
        msg = userName + "说：" + msg;
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        //发送信息至服务端
        socketChannel.write(byteBuffer);
    }

    //从服务端读取信息（接收信息）
    private void readMsg() throws IOException {
        int count = selector.select();
        if (count > 0) {//有可用的通道
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    //读取数据
                    channel.read(byteBuffer);
                    //把读到缓冲区的数据转成字符串
                    String msg = new String(byteBuffer.array());
                    System.out.println(msg.trim());
                }
                iterator.remove();
            }
        } else {
//            System.out.println("没有可用的通道....");

        }
    }

    public static void main(String[] args) throws Exception {
        //启动客户端
        GroupChatClient groupChatClient = new GroupChatClient();
        //启动一个线程，每隔3秒读取一次
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        groupChatClient.readMsg();
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        //发送数据给服务器
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            groupChatClient.sendMsg(s);
        }
    }
}
