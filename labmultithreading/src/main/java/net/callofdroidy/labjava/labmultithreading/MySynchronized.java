package net.callofdroidy.labjava.labmultithreading;

/**
 * Created by admin on 11/08/15.
 */

/**
 * There are 2 ways to use 'synchronized', one is synchronize an method(like the example shown below),
 * the other one is synchronize a block
 * Assume there is a bank account and 100 threads try to access it, each save and withdraw 100 bucks.
 * without synchronized, the account's 'total' will be accessed by multiple threads at the same time,
 * and cause incorrect result
 *
 * See how to deal with this in Scala using val later
 */

public class MySynchronized {

    public static void main(String[] args){
        int NUM_OF_THREAD = 100;
        Thread[] threads = new Thread[NUM_OF_THREAD];

        final Account acc = new Account("John", 1000);

        for (int i = 0; i< NUM_OF_THREAD; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    acc.deposit(100);
                    acc.withdraw(100);
                }
            });
            threads[i].start();
        }

        for (int i=0; i<NUM_OF_THREAD; i++){
            try {
                threads[i].join(); //wait till all threads finish
            } catch (InterruptedException e) {
                // ignore
            }
        }
        System.out.println("Finally, John's balance is:" + acc.getBalance());
    }
}

class Account {
    String name;
    int total;

    public Account(String name, int amount) {
        this.name = name;
        this.total = amount;
    }

    public synchronized void deposit(int deposit) {
        try {
            System.out.println("processing deposit " + Thread.currentThread().getName());
            total += deposit;
            Thread.sleep(100); //simulate time-consuming processing
        } catch (InterruptedException e) {
        }

    }

    public synchronized void withdraw(int withdraw) {
        try {
            System.out.println("processing withdraw " + Thread.currentThread().getName());
            Thread.sleep(100); //simulate time-consuming processing
            total -= withdraw;
        } catch (InterruptedException e) {
        }
    }

    public int getBalance() {
        return total;
    }
}

/**
 * the other way to use synchronized-(block)----simple example
 * public void method3(SomeObject obj){
 *   synchronized(obj){
 *   //do something
 *   }
 * }
 */

