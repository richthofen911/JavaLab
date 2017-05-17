package net.callofdroidy.labjava.labmultithreading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by admin on 10/08/15.
 */
public class FutureTaskDemo {

    public static void main(String[] args){
        //initialize a Callable obj & a FutureTask obj
        Callable privateAccount = new PrivateAccount();
        FutureTask futureTask = new FutureTask(privateAccount);
        //use futureTask to create a thread
        Thread threadPrivateAccount = new Thread(futureTask);
        System.out.println("futureTask线程现在开始启动，启动时间为： current time: " + System.nanoTime());
        threadPrivateAccount.start();
        System.out.println("main thread starts to calculate your other accounts money");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //main thread get total amount from other accounts
        int totalMoney = new Random().nextInt(100000);
        System.out.println("Current amount in your other accounts: " + totalMoney);
        System.out.println("main thread has finished it's job, waiting for the results of your private account...");

        while (!futureTask.isDone()){
            try{
                Thread.sleep(1000);
                System.out.println("still waiting..." + System.nanoTime());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("futureTask thread is finished, current time: " + System.nanoTime());
        Integer privateAccountMoney = null;
        try{
            privateAccountMoney = (Integer) futureTask.get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("You current total amount from all accounts: " + (totalMoney + privateAccountMoney.intValue()));
    }
}

class PrivateAccount implements Callable{
    Integer totalMoney;

    @Override
    public Object call() throws Exception{
        Thread.sleep(5000);
        totalMoney = new Random().nextInt(10000);
        System.out.println("Current amount in your private account: " + totalMoney);
        return totalMoney;
    }
}
