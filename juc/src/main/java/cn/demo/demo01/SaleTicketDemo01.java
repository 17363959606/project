package cn.demo.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * title :三个售票员  卖出 30张票
 *
 * @describe 如何编写企业级的多线程代码
 * 1.在高内聚低耦合的前提下， 线程        操作          资源类
 * 1.1 先创建一个资源类
 * 线程的几种状态：
 * 查看这个类Thread.State  6个状态
 * 1.NEW()  尚未启动的线程的线程状态。
 * 2.RUNNABLE()可运行线程的线程状态
 * 3.BLOCKED() 等待监视器锁定时阻塞的线程的线程状态 "阻塞"
 * 4.WAITING() 等待线程的线程状态
 * 5.TIMED_WAITING() 具有指定等待时间的等待线程的线程状态
 * 6.TERMINATED() 终止线程的线程状态。
 */

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 1; i <= 30; i++) { ticket.sale(); }},"A").start();
        new Thread(() -> { for (int i = 1; i <= 30; i++) { ticket.sale(); }},"B").start();
        new Thread(() -> { for (int i = 1; i <= 30; i++) { ticket.sale(); }},"C").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i <= 30; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "A").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i <= 30; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i <= 30; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "C").start();


    }

}

/**
 * 资源类 = 实例变量 + 实例方法
 */
class Ticket {
    private int number = 30;

    // List list = new ArrayList();  左边接口，右边是实现类
    //ReentrantLock  可重复锁
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t" + "卖出第：" + number-- + "\t还剩下：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
