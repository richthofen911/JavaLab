package net.callofdroidy.labjava.labmultithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by admin on 10/08/15.
 */
public class MyBlockingQueue extends Thread{
    public static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);
    private int index;

    public MyBlockingQueue(int i){
        this.index = i;
    }

    public void run(){
        try{
            blockingQueue.put(String.valueOf(this.index));
            System.out.println("{" + this.index + "} in queue, current blockingQueue: " + MyBlockingQueue.blockingQueue.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++){
            executorService.submit(new MyBlockingQueue(i));
        }
        Thread thread = new Thread(){
            public void run(){
                try{
                    while(true){
                        Thread.sleep(1000);
                        if(MyBlockingQueue.blockingQueue.isEmpty())
                            break;
                        //take() method retrieves and removes the head of this queue, waiting if necessary until
                        //an element becomes available.
                        String str = MyBlockingQueue.blockingQueue.take();
                        System.out.println(str + " has been token, current blockingQueue: " + MyBlockingQueue.blockingQueue.toString());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        executorService.submit(thread);
        executorService.shutdown();
    }
}
