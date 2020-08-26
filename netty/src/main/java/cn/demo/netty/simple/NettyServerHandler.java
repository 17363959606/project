package cn.demo.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 我们自定义的一个Handler需要继承netty规定好的某个HandlerAdapter（规范）
 * 这时我们自定义的一个handler才能被称为一个handler
 */
//入站handler
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实习（这里我们可以读取客户端发送的信息）
    /*
     1.ChannelHandlerContext ctx 上下问对象，含有管道pipeline,通道channel地址
     2.Object msg 就是客户端发送的数据默认object
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //当然可以存在多个异步任务
        //如果存在耗时很长的业务 ->异步执行 -> 提交给channel对应的NioEventLoop的taskQueue
        //解决方法1：用户程序定义普通任务
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //睡眠10s
                    Thread.sleep(10000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("10s耗时数据服务器已处理", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //睡眠20,由于在同一个线程中所以这里的睡眠20s必须在上一个任务执行完后再开始处理。相当于30s了
                    Thread.sleep(20000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("20s耗时数据服务器已处理", CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        //解决方法2： 用户自定义定时任务
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    ctx.writeAndFlush(Unpooled.copiedBuffer("schedule耗时数据服务器已处理", CharsetUtil.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println("server ctx : " + ctx);
        //ByteBuf 是netty提供的，性能比NIO的ByteBuffer高
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端发送的信息是：" + byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端的地址：" + ctx.channel().remoteAddress());
    }


    //数据读取完毕（发送数据）
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //writeAndFlush
        //将数据写入到缓存并刷新，一般情况下我们对这个发送的数据进行编码
        System.out.println("服务端发送信息打印：hello，我是服务端_____");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello，我是服务端_____", CharsetUtil.UTF_8));
    }

    //处理异常，一般是需要关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }

}
