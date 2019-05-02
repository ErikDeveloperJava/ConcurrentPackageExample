package cocurrent.example.atomic_classes_example;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Through AtomicInteger class available access to Integer number from many threads by ordering
 **/
public class AtomicIntegerExample {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        createThread("t1");
        createThread("t2");
        createThread("t3");
        createThread("t4");
    }

    private static void createThread(String threadName){
        new Thread(() -> {
            for (int i = 0; i < 200; i++) {
                changeNumber(i+1);
            }
        },threadName)
                .start();
    }

    @SneakyThrows
    private static void changeNumber(int number){
        int currentNumber = atomicInteger.get();
        System.out.println(Thread.currentThread().getName() + " : currentNumber --> " + currentNumber);
        atomicInteger.set(number);
        System.out.println(Thread.currentThread().getName() + " : changed currentNumber to -->" + number);
        Thread.sleep(2000);
    }
}
