package com.sir.interview.thread.abc.method2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright
 * FileName: ConditionDemo
 * Description:
 * :
 *
 * @author sir
 * @create 2019/1/1 17:15
 * @since 1.0.0
 */
public class ConditionDemo {

    //可重入锁
    private static Lock lock = new ReentrantLock();
    //条件A
    private static Condition A = lock.newCondition();
    //条件B
    private static Condition B = lock.newCondition();
    //条件C
    private static Condition C = lock.newCondition();
    //计数器
    private static int count = 0;

    /**
     * 线程A
     */
    static class ThreadDemoA implements Runnable {

        public void run() {
            while (true) {
                lock.lock();
                try {
                    //对于线程A若计数器是3的倍数就输出，否则等待
                    while (count % 3 != 0) A.await();
                    System.out.println("A");
                    count++;
                    //唤醒下一个要执行的线程
                    B.signal();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    /**
     * 线程B
     */
    static class ThreadDemoB implements Runnable {

        public void run() {

            while (true) {
                lock.lock();
                try {
                    while (count % 3 != 1) B.await();
                    System.out.println("B");
                    count++;
                    C.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 线程C
     */
    static class ThreadDemoC implements Runnable {

        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (count % 3 != 2) C.await();
                    System.out.println("C");
                    count++;
                    A.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {

        Thread A = new Thread(new ThreadDemoA(), "A");
        Thread B = new Thread(new ThreadDemoB(), "B");
        Thread C = new Thread(new ThreadDemoC(), "C");
        A.start();
        B.start();
        C.start();
    }


}
