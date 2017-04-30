package ru.maddness.cracking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author maddness
 */
public class FizzBuzz {

    private static AtomicInteger number = new AtomicInteger(0);
    private static final Object lock = new Object();

    private static ExecutorService executor = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        executor.execute(() -> {
            while (true) {
                synchronized (lock) {
                    if (number.get() % 3 == 0 && number.get() % 5 == 0) {
                        System.out.println("FuzzBuzz");
                        number.incrementAndGet();
                    }
                }
            }
        });
        executor.execute(() -> {
            while (true) {
                synchronized (lock) {
                    if (number.get() % 3 == 0 && number.get() % 5 != 0) {
                        System.out.println("Fuzz");
                        number.incrementAndGet();
                    }
                }
            }
        });
        executor.execute(() -> {
            while (true) {
                synchronized (lock) {
                    if (number.get() % 3 != 0 && number.get() % 5 == 0) {
                        System.out.println("Buzz");
                        number.incrementAndGet();
                    }
                }
            }
        });
        executor.execute(() -> {
            while (true) {
                synchronized (lock) {
                    if (number.get() % 3 != 0 && number.get() % 5 != 0) {
                        System.out.println(number);
                        number.incrementAndGet();
                    }
                }
            }
        });
    }
}
