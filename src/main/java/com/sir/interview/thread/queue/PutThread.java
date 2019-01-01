package com.sir.interview.thread.queue;

/**
 * Copyright
 * FileName: PutThread
 * Description:
 * :
 *
 * @author sir
 * @create 2019/1/2 0:31
 * @since 1.0.0
 */
public class PutThread implements Runnable {

    private int num;
    private BoundedBuffer boundedBuffer;

    public PutThread(int num, BoundedBuffer boundedBuffer) {
        this.num = num;
        this.boundedBuffer = boundedBuffer;
    }

    public void run() {
        try {
            boundedBuffer.put(num);
//            System.out.println(String.format("线程%s put num:%d", Thread.currentThread().getName(), num));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
