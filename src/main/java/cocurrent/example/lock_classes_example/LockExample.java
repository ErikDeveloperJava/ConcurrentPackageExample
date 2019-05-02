package cocurrent.example.lock_classes_example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock can block method than free it
 **/
public class LockExample {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        createThread("t1");
        createThread("t2");
        createThread("t3");
        createThread("t4");
        createThread("t5");
    }

    private static void createThread(String threadName){
        new Thread(LockExample::testMethod,
                threadName)
                .start();
    }

    private static void testMethod(){
        //lock.lock() means that current thread blocked this method
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " is in the testMethod()");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //lock.unlock() means that current thread freed this method
        lock.unlock();
    }
}