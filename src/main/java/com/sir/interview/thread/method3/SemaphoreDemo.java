package com.sir.interview.thread.method3;

import java.util.concurrent.Semaphore;

/**
 * Copyright
 * FileName: SemaphoreDemo
 * Description:
 * :
 *
 * @author sir
 * @create 2019/1/1 17:37
 * @since 1.0.0
 */
public class SemaphoreDemo {


    public static void main(String[] args) throws InterruptedException {
        Semaphore A = new Semaphore(1);
        Semaphore B = new Semaphore(1);
        Semaphore C = new Semaphore(1);
        B.acquire();
        C.acquire();
        new Thread(new SemaphoreThread(A, B),"A").start();
        new Thread(new SemaphoreThread(B, C),"B").start();
        new Thread(new SemaphoreThread(C, A),"C").start();
    }

}
