package com.sir.interview.thread.queue;

/**
 * Copyright
 * FileName: TakeThread
 * Description:
 * :
 *
 * @author sir
 * @create 2019/1/2 0:35
 * @since 1.0.0
 */
public class TakeThread implements Runnable {

    private BoundedBuffer boundedBuffer;

    public TakeThread(BoundedBuffer boundedBuffer) {
        this.boundedBuffer = boundedBuffer;
    }

    public void run() {
        try {
            boundedBuffer.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
