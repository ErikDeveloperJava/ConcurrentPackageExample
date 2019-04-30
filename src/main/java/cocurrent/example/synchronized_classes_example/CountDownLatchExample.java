package cocurrent.example.synchronized_classes_example;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch works such wait() and notify() methods int Object class
 * it can work by calling count of notify() methods
 **/
public class CountDownLatchExample {

    private static String text;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                //countDownLatch.await() means that current thread will block until another thread does not call countDownLatch.CountDown() method
                System.out.println(Thread.currentThread().getName() + " is waiting for t2");
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName() + " has already free");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("text : " + text);
        },"t1").start();

        new Thread(() -> {
            System.out.println("t2 started");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            text = "Hello World";
            System.out.println("t2 finished");
            countDownLatch.countDown();
        },"t2").start();
    }
}