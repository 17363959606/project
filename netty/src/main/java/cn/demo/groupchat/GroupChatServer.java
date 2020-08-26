package cn.demo.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 编写聊天系统服务端
 */
public class GroupChatServer {
    //定义常用的参数
    //用来监听的管道
    private ServerSocketChannel listenChannel;
    //定义选择器
    private Selector selector;
    //定义端口地址
    private static final int PORT = 6667;

    //构造器初始化参数
    public GroupChatServer() {
        try {
            //打开服务端连接
            listenChannel = ServerSocketChannel.open();
            //设置为阻塞模式
            listenChannel.configureBlocking(false);
            //监听指定的端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //打开选择器
            selector = Selector.open();
            //将listenChannel注册到选择器,监听连接事件
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //监听服务
    private void listen() {
        System.out.println("服务器启动成功...");
        while (true) {
            try {
                //阻塞2秒没有2000就一直阻塞到有数据
                int count = selector.select(2000);
                if (count > 0) {
                    //获取到连接成功的key
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    if (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        //判断Key的状态
                        if (key.isAcceptable()) {
                            //获取到相关的socketChannel
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            //提示
                            System.out.println(socketChannel.getRemoteAddress() + "上线");
                        }
                        if (key.isReadable()) {//通过管道发送read事件，即管道是可读状态
                            //读处理..
                            read(key);
                        }
                        //删除当前的key，防止重复操作
                        iterator.remove();
                    }
                } else {
                    //System.out.println("等待中....");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //read操作
    private void read(SelectionKey key) {
        //得到相应的socketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int count = socketChannel.read(byteBuffer);
            //根据count值做处理
            if (count > 0) {
                String msg = new String(byteBuffer.array());
                System.out.println("服务器端接收信息：" + msg);
                //转发信息值其他客户端（排除发送信息本身的客户端）
                sendMsgToOtherChannel(msg, socketChannel);
            }
        } catch (IOException e) {
            try {
                //通过read异常来判断用户是否下线
                System.out.println(socketChannel.getRemoteAddress() + "离线了..");
                //取消注册
                key.cancel();
                //关闭通道
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //转发信息至其他客户端(排除发送消息端的自己)
    private void sendMsgToOtherChannel(String msg, SocketChannel self) {
        System.out.println("服务器转发信息中...");
        //遍历所有注册到selector上的SocketChannel并排除self
        for (SelectionKey key : selector.keys()) {
            //通过key取出对应的SocketChannel
            SelectableChannel channel = key.channel();
            //排除自己
            if (channel instanceof SocketChannel && channel != self) {
                //将mgs存储到buffer
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    //将buffer的数据写入通道
                    SocketChannel socketChannel = (SocketChannel) channel;
                    socketChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
