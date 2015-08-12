package net.callofdroidy.labjava.multithread;

/**
 * Created by admin on 12/08/15.
 */
public class MyVolatile {

    public static void main(String[] args){
        State oneState = new State();

        new Thread(){
            public void run(){
                System.out.println("side thread started");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                oneState.setStatus(true);
            }
        }.start();

        while(!oneState.getStatus()){
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("not stopped yet");
        }

        System.out.println("stopped");

    }

}

class State{
    private volatile boolean status = false;

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean newStatus){
        status = newStatus;
    }
}
