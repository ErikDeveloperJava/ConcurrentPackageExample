package cocurrent.example.collection_classes_example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap can work in multithreading environment
 * At first in ConcurrentHashMap creates HashMap[]
 * that Array need to because every thread will be able to work without depends from another thread
**/
public class ConcurrentHashMapExample {

    private static Map<Integer,String> concurrentHashMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        //here each thread will work on own HashMap object without disturbing another threads
        Thread t1 = createThread(0);
        Thread t2 = createThread(10);
        Thread t3 = createThread(20);
        Thread t4 = createThread(30);
        Thread t5 = createThread(40);
        Thread t6 = createThread(60);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();


        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();

        System.out.println("***************** FINALLY RESULT ******************");
        concurrentHashMap
                .keySet()
                .forEach(key -> {
                    System.out.println(key  + " : " + concurrentHashMap.get(key));
                });
    }

    private static Thread createThread(int startNumber){
        return new Thread(() -> {
            for (int i = startNumber; i < startNumber+10; i++) {
                concurrentHashMap.put(i,"Text_" + i);
            }
        });
    }
}
