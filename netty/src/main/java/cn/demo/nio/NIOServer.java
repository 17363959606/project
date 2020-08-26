package cn.demo.nio;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 编写Netty服务端
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建serverSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个Selector对象
        Selector selector = Selector.open();
        //绑定一个端口6666在服务器监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到selector,关心事件为OP_ACCEPT（连接事件）
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true) {
            //这里等待1秒，如果没有事件发生，返回
            if (selector.select(1000) == 0) {//没有事件发生
                System.out.println("服务器等待了1秒，无连接");
                continue;
            }
            //如果返回的>0,就获取到相关的selectionKey集合
            //1.如果返回的>0，表示已经获取到关注的事件
            //2.selector.selectedKeys() 返回关注事件的集合
            //selectedKeys可以反向获取通道
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历selectionKeys
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                //获取到SelectionKey
                SelectionKey key = keyIterator.next();
                //根据key对应的通道发生的事件进行相应的处理
                if (key.isAcceptable()) {//如果是op_accept表示有新的客户端连接
                    //给该客户端生产一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //将socketChannel设置为非阻塞de
                    socketChannel.configureBlocking(false);
                    //将当前的socketChannel注册到selector,关注事件为op_read,同时给该socketChannel关联一个buffer
                    System.out.println("成功连接一个客户端：" + socketChannel.hashCode());
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } else if (key.isReadable()) {//发生了op_read事件
                    //通过key反向获取对应的channel
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socketChannel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));
                }
                //手动从当前的集合中删除当前的selectionKey,防止重复操作
                keyIterator.remove();
            }
        }
    }
}
