package cocurrent.example.synchronized_classes_example;

import java.util.concurrent.Exchanger;

/**
 * Exchanger<T> can transfer object from one thread to another Thread
 **/
public class ExchangerExample {

    private static StringBuilder builder;

    private static Exchanger<StringBuilder> exchanger = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start");
            try {
                //exchanger.exchange() means that this thread will block unless another thread will not call exchanger.exchange() method and transfer BuilderObject
                StringBuilder exchangeBuild = exchanger.exchange(builder);
                Thread.sleep(2000);
                exchangeBuild.append(" World");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 finished");
        },"t1");
        Thread t2 = new Thread(() -> {
            System.out.println("t2 start");
            try {
                Thread.sleep(2000);
                builder = new StringBuilder();
                builder.append("Hello");
                exchanger.exchange(builder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 finished");
        },"t1");
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("finally result --> " + builder.toString());
    }
}