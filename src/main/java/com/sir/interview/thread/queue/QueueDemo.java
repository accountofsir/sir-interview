package com.sir.interview.thread.queue;

/**
 * Copyright
 * FileName: QueueDemo
 * Description:
 * :
 *
 * @author sir
 * @create 2019/1/2 0:18
 * @since 1.0.0
 */

public class QueueDemo {

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        for (int i = 0; i < 10; i++) {
            new Thread(new PutThread(i, boundedBuffer), "put_" + i).start();
            new Thread(new TakeThread(boundedBuffer), "take_" + i).start();
        }
    }

}


