package cocurrent.example.lock_classes_example;

/**
 * Condition can manage to threads more flexible it is similar to wait() and notify() int Object class
**/
public class ConditionExample {

    public static void main(String[] args) throws InterruptedException {
        IntegerUtil integerUtil = new IntegerUtil();
        Thread addValueThread = new Thread(() -> {
            int value = 1;
            while (true) {
                integerUtil.add(value++);
            }
        }, "add_value_thread");
        Thread printLastNumber = new Thread(() -> {
            while (true) {
                integerUtil.printLastNumber();
            }
        }, "print_last_number");
        Thread print_count = new Thread(() -> {
            while (true) {
                integerUtil.printCount();
            }
        }, "print_count");

        addValueThread.start();
        printLastNumber.start();
        print_count.start();

        addValueThread.join();
        printLastNumber.join();
        print_count.join();
    }
}
