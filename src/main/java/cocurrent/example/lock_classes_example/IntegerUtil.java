package cocurrent.example.lock_classes_example;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IntegerUtil {

    private List<Integer> integerList = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int statusNumber;


    @SneakyThrows
    public void add(int value){
        lock.lock();
        while (statusNumber != 0){
            condition.await();
        }
        System.out.println(Thread.currentThread().getName() + " add value '" + value + "' in list");
        Thread.sleep(4000);
        integerList.add(value);
        statusNumber = 1;
        condition.signalAll();
    }

    @SneakyThrows
    public void printLastNumber(){
        lock.lock();
        while (statusNumber != 1){
            condition.await();
        }
        System.out.println(Thread.currentThread().getName() + " print last number of list");
        Thread.sleep(4000);
        System.out.println("last number : " + integerList.get(integerList.size() -1));
        statusNumber = 2;
        condition.signalAll();
    }

    @SneakyThrows
    public void printCount(){
        lock.lock();
        while (statusNumber != 2){
            condition.await();
        }
        System.out.println(Thread.currentThread().getName() + " print count numbers of list");
        Thread.sleep(4000);
        int sum = integerList
                .stream()
                .reduce(0, (i1, i2) -> i1 + i2);
        System.out.println("sum : " + sum);
        statusNumber = 0;
        condition.signalAll();
    }
}
