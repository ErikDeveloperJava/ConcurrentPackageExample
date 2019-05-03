package cocurrent.example.collection_classes_example;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * CopyOnWriteArrayList can work in multithreading environment
 * every thread when invoke method of collection in collection created copy of array in collection
 * During the iteration has never thrown Exception when suddenly collection updates
**/
public class CopyOnWriteArrayListExample {

    private static List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        AtomicBoolean isFinishedT1 = new AtomicBoolean(false);
        AtomicBoolean isFinishedT2 = new AtomicBoolean(false);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                copyOnWriteArrayList.add(i);
                System.out.println("t1 add '" + i +"'");
            }
            isFinishedT1.set(true);
        });
        Thread t2 = new Thread(() -> {
            for (int i = 30; i < 60; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                copyOnWriteArrayList.add(i);
                System.out.println("t2 add '" + i +"'");
            }
            isFinishedT2.set(true);
        });
        Thread t3 = new Thread(() -> {
            while (!isFinishedT1.get() || !isFinishedT2.get()) {
                for (Integer value : copyOnWriteArrayList) {
                    if (value % 2 == 0) {
                        copyOnWriteArrayList.remove(value);
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        //here we can see finally result
        System.out.println("***************** FINALLY RESULT ************************");
        for (Integer value : copyOnWriteArrayList) {
            System.out.println("value: " + value);
        }
    }
}
