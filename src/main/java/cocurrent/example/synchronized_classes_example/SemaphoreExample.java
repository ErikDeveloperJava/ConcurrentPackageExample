package cocurrent.example.synchronized_classes_example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * * Semaphore can synchronize by count of the Thread
 * * Semaphore allows how many threads can be in the method of the class at that moment
 * */
public class SemaphoreExample {

    private static List<Integer> listForPrint = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        createThreadByName("t1");
        createThreadByName("t2");
        createThreadByName("t3");
        createThreadByName("t4");
        createThreadByName("t5");
        createThreadByName("t6");
    }

    private static void createThreadByName(String threadName){
        new Thread(SemaphoreExample::testMethod,threadName)
                .start();
    }

    private static void testMethod(){
        //this method means that currentThread synchronizes this method
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("************************** THREAD -> " + Thread.currentThread().getName() + " ***************************");
        listForPrint.forEach(value -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(value);
        });
        System.out.println();

        //this method means that currentThread fires this method from synchronizing
        semaphore.release();
    }
}