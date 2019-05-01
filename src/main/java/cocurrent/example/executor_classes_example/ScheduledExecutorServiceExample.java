package cocurrent.example.executor_classes_example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService different from ExecutorService that it can invoke and repeat async methods by time
 **/
public class ScheduledExecutorServiceExample {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    private static int callCount = 1;

    public static void main(String[] args) {
        //this will invoke every 2 seconds later
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println(Thread.currentThread()
                        .getName() + " scheduleWithFixedDelay(), count : " +  callCount++),
                2L,2L, TimeUnit.SECONDS);
    }
}