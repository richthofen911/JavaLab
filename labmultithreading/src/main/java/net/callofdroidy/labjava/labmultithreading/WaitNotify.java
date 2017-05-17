package net.callofdroidy.labjava.labmultithreading;

/**
 * Created by admin on 11/08/15.
 */
public class WaitNotify {
    public static Object object = new Object();

    static class Thread0 extends Thread{
        @Override
        public void run(){
            synchronized (object){
                try{
                    object.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " got the lock");
            }
        }
    }

    static class Thread1 extends Thread{
        @Override
        public void run(){
            synchronized (object){
                object.notify();
                System.out.println("Thread " + Thread.currentThread().getName() + " invoked object.notify()");

            }
            System.out.println("Thread " + Thread.currentThread().getName() + " released the lock");
        }
    }

    public static void main(String[] args){
        Thread0 thread0 = new Thread0();
        Thread1 thread1 = new Thread1();

        thread0.start();

        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        thread1.start();
    }
}
