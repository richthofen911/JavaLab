package net.callofdroidy.labjava.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 11/08/15.
 */
public class MyCountDownLatch {
    public static void main(String[] args) throws InterruptedException{
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);

        final ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int index = 0; index < 10; index++){
            final int NO = index + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try{
                        begin.await();
                        Thread.sleep(2000);
                        System.out.println("No." + NO + " arrived");
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }
                }
            };
            executorService.submit(runnable);
        }
        System.out.println("Game start");
        begin.countDown();
        end.await();
        System.out.println("Game over");
        executorService.shutdown();
    }
}
