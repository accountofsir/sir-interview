package com.sir.interview.thread.method1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Copyright
 * FileName: ThreadDemo01
 * Description:
 * :题：A B C三个线程分别打印A,B,C 用多线程实现循环打印出ABC
 *
 * @author sir
 * @create 2019/1/1 16:49
 * @since 1.0.0
 */
public class ThreadDemo01 {

    public static void main(String[] args) {

        Thread001 p1 = new Thread001();
        Thread001 p2 = new Thread001();
        Thread001 p3 = new Thread001();

        Thread A = new Thread(p1, "A");
        Thread B = new Thread(p2, "A");
        Thread C = new Thread(p3, "A");

        A.start();
        B.start();
        C.start();
    }

    static class Thread001 implements Runnable {

        private static final Logger logger = Logger.getLogger("log");
        private static final AtomicInteger count = new AtomicInteger(0);

        public void run() {
            while (true) {
                synchronized (count) {
                    if (count.get() % 3 == 0) {
                        logger.info("A");
                    } else if(count.get() % 3 == 1){
                        logger.info("B");
                    }else {
                        logger.info("C");
                    }

                    count.incrementAndGet();
                }
            }
        }
    }


}
