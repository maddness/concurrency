package ru.maddness.cracking;

/**
 * @author aostrikov
 *         <p>
 *         15.3 Non-locking, but may be not optimal
 */
public class Philosopher extends Thread {

    private final Fork left;
    private final Fork right;
    private final int number;

    public Philosopher(Fork left, Fork right, int number) {
        this.left = left;
        this.right = right;
        this.number = number;
    }

    @Override
    public void run() {
        while (true) {
            if (number % 2 == 0) {
                eat(right, left);
            } else {
                eat(left, right);
            }
        }
    }

    private void eat(Fork one, Fork two) {
        synchronized (one) {
            synchronized (two) {
                try {
                    System.out.println(number + " is eating");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();

        Philosopher p1 = new Philosopher(fork1, fork2, 0);
        Philosopher p2 = new Philosopher(fork2, fork3, 1);
        Philosopher p3 = new Philosopher(fork3, fork1, 2);

        p1.start();
        p2.start();
        p3.start();
    }

    private static class Fork {
    }
}
