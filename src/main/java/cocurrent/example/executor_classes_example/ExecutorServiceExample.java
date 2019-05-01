package cocurrent.example.executor_classes_example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorService can invoke async methods
 **/
public class ExecutorServiceExample {


    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception{

        //submit method allows get result
        executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " : submit() start");
        }).get();

        //execute method is void
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " : execute() start");
        });

        //invokeAll allows invoke many tasks in different threads
        executorService.invokeAll(createTasks())
                .forEach(future -> {
                    try {
                        System.out.println(future.get());
                    }catch (Exception e){

                    }
                });

        executorService.shutdown();
    }

    private static List<Callable<String>> createTasks(){
        return Arrays.asList(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " : invokeAll() start");
                    return "text 1";
                },
                () -> {
                    System.out.println(Thread.currentThread().getName() + " : invokeAll() start");
                    return "text 2";
                },
                () -> {
                    System.out.println(Thread.currentThread().getName() + " : invokeAll() start");
                    return "text 3";
                },
                () -> {
                    System.out.println(Thread.currentThread().getName() + " : invokeAll() start");
                    return "text 4";
                }
        );
    }
}
