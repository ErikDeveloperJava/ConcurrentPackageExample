package cocurrent.example.synchronized_classes_example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier can block many Threads by count which is set previous
 * CyclicBarrier can call another thread when count of blocking threads are full
 **/
public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3,() -> {
        //this thread will call when 3 thread will be blocking
        System.out.println("another thread start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("another thread finish");
    });

    public static void main(String[] args) throws InterruptedException {
        createThreadByName("t1");
        createThreadByName("t2");
        createThreadByName("t3");
    }

    private static void createThreadByName(String name){
        new Thread(() -> {
            try {
                //cyclicBarrier.await() means that current thread will block until another thread will not finish work
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " has already free");
        },name).start();
    }
}
