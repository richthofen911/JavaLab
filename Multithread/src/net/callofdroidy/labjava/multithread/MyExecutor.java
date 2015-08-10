package net.callofdroidy.labjava.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Tuotuo on 09/08/2015.
 */
public class MyExecutor extends Thread {
    private int index;

    public MyExecutor(int i){
        this.index = i;
    }
    public void run(){
        try{
            System.out.println("["+this.index+"] start....");
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println("["+this.index+"] end.");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for(int i = 0; i < 10; i++){
            executorService.execute(new MyExecutor(i));
        }
        System.out.println("submit finish");
        executorService.shutdown();
    }
}
