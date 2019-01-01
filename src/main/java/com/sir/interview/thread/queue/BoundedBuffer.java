package com.sir.interview.thread.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright
 * FileName: BoundedBuffer
 * Description:
 * :
 *
 * @author sir
 * @create 2019/1/2 0:20
 * @since 1.0.0
 */
public class BoundedBuffer {

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private Object[] items = new Object[5];
    private int putPoint, takePoint, count;

    public void put(Object object) throws InterruptedException {
        lock.lock();
        try {
            //队列满了 等待
            while (count == items.length) {
                notFull.await();
            }
            items[putPoint] = object;
            //若队列已满 则下次应put在0位置上
            if (++putPoint == items.length) {
                putPoint = 0;
            }
            System.out.println(Thread.currentThread().getName() + "put_" + object);
            //计数
            count++;
            //唤醒读
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            //队列为空则等待
            while (count == 0) {
                notEmpty.await();
            }
            Object ret = items[takePoint];
            //到队列末尾了 下次应获取0位置
            if (++takePoint == items.length) {
                takePoint = 0;
            }
            System.out.println(Thread.currentThread().getName() + "take_" + ret);
            //计数减一
            count--;
            //唤醒写
            notFull.signal();
            return ret;
        } finally {
            lock.unlock();
        }
    }
}
