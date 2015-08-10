package net.callofdroidy.labjava.multithread;

/**
 * Created by admin on 05/08/15.
 */

class Sum {
    private int sum;

    public int getSum(){
        return sum;
    }

    public void setSum(int value){
        this.sum = value;
    }
}

class Summation implements Runnable{
    private int upper;
    private Sum sumValue;

    public Summation(int upper, Sum sumValue){
        this.upper = upper;
        this.sumValue = sumValue;
    }

    public void run(){
        int sum = 0;
        for(int i = 0; i <= upper; i++)
            sum += i;
        sumValue.setSum(sum);
    }
}

public class Basic {
    public static void main(String[] args){
        int upper = 5;
        Sum sum = new Sum();
        //the constructor of Thread accept an class implementing run() method
        Thread thread = new Thread(new Summation(upper, sum));
        thread.start();
        try{
            thread.join(); //join() blocks main thread, waits for the summation thread till its finished
            System.out.println("the sum of " + upper + " is " + sum.getSum());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
