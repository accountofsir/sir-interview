package com.sir.interview.thread.abc.method3;

import java.util.concurrent.Semaphore;

/**
 * Copyright
 * FileName: SemaphoreThread
 * Description:
 * :
 *
 * @author sir
 * @create 2019/1/1 23:16
 * @since 1.0.0
 */
public class SemaphoreThread implements Runnable {

    private Semaphore target;
    private Semaphore next;
    public SemaphoreThread(Semaphore semaphore,Semaphore next) {
        this.target = semaphore;
        this.next = next;
    }

    public void run() {
        while (true) {
            try {
                target.acquire(1);
                System.out.println(Thread.currentThread().getName());
                next.release(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
