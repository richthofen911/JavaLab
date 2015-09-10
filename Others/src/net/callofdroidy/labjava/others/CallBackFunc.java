package net.callofdroidy.labjava.others;

/**
 * Created by richthofen80 on 9/9/15.
 * 回调函数就是函数指针针的一种用法, 原本是C里的用法，C里函数的名就是它的指针。Java中没有指针，所以用Interface来实现实现这种用法
 * http://blog.csdn.net/fengyifei11228/article/details/5729445
 */
public class CallBackFunc {
    public static void main(String args[]) {
        Caller call = new Caller();
        call.setCallFunc(new B());
        call.call();
    }
}

interface MyCallInterface {
    void method();
}

class Caller {
    public MyCallInterface mc;

    public void setCallFunc(MyCallInterface mc) {
        this.mc= mc;
    }

    public void call(){
        this.mc.method();
    }
}

class B implements MyCallInterface {
    public void method() {
        System.out.println("classB CallBack by classA");
    }
}



