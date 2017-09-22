/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day1.e1;

public class Exercise1 {

    public static void main(String[] args) throws InterruptedException
    {
        SumNumber thread1 = new SumNumber();
        FiveCount thread2 = new FiveCount();
        Thread3 thread3 = new Thread3();

        Thread t1 = new Thread(thread1, "sum thread");
        Thread t2 = new Thread(thread2, "fiveCount thread");
        Thread t3 = new Thread(thread3, "TenCount thread");

        /*
        Answer the following questions:
        For thread3 we need (at least in theory) to use the synchronization 
        techniques we’ve learned today.        
        a) Do you observe a need for synchronization techniques on any of the 
        threads in practise, or does your program work by accident?
            It works because there is no need for synchronized.
        
            
        
        b) Regardless of whether you observe the problem on your machine, we 
        need to handle it.
        What is the problem I’m hinting at, and how can we solve it? (Since I’m 
        such a nice guy, here’s a hint: think about the VolatileExample class 
        from the demo today).
            Is the problem that, thread3 can end up doing an extra count if you 
            change the value while the thread is sleeping inside the while loop?
            Can be fixed by checking the boolean value after the thread have 
            been sleeping.
        
        c) argue that your solution is correct (argue, don’t prove)
            Tbh t2 takes about 10sec to complete, so couldn't we just see if that 
            thread is alive and then interrupt t3 if not? I do not understand
            how else to fix the problem with the extra 3sec sleep.
         */
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(10000);
        thread3.setIsCounting(false);
    }
}
