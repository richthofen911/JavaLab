package net.callofdroidy.labjava.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by admin on 10/08/15.
 */
//scenario: assume there's a washroom having 2 positions and there are 10 people need to line up

public class MySemaphore extends Thread{
    Semaphore position;
    private int id;

    public MySemaphore(int i, Semaphore s){
        this.id = i;
        this.position = s;
    }

    public void run(){
        try{
            if(position.availablePermits() > 0){
                System.out.println("person[" + this.id + "] enter the washroom, still available position");
            }else {
                System.out.println("Person[" + this.id + "] enter the washroom, no position available");
            }
            position.acquire();
            System.out.println("person[" + this.id + "] get a position");
            Thread.sleep(2000);
            System.out.println("person[" + this.id + "] finished, available position amount: " + position.availablePermits());
            position.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Semaphore position = new Semaphore(2, true);
        for(int i = 0; i < 10; i++){
            executorService.submit(new MySemaphore(i + 1, position));
        }
        executorService.shutdown();
        while(!executorService.isTerminated()){
            continue;
        }System.out.println("all 10 people have used the washroom, clean up time");

    }
}
