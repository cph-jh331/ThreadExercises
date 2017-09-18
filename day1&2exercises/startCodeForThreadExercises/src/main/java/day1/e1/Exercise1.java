/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day1.e1;

public class Exercise1 {

    public static void main(String[] args) throws InterruptedException
    {
        SumNumber sum = new SumNumber();
        FiveCount fiveCount = new FiveCount();
        TenCount tenCount = new TenCount();

        Thread t1 = new Thread(sum, "sum thread");
        Thread t2 = new Thread(fiveCount, "fiveCount thread");
        Thread t3 = new Thread(tenCount, "TenCount thread");

        /*
        Answer the following questions:
        For thread3 we need (at least in theory) to use the synchronization 
        techniques we’ve learned today.        
        a) Do you observe a need for synchronization techniques on any of the 
        threads in practise, or does your program work by accident?
            By making the main thread sleep for 10secs, it for some reason
            still runs the method one last time. So we get one count to many?
            
        
        b) Regardless of whether you observe the problem on your machine, we 
        need to handle it.
        What is the problem I’m hinting at, and how can we solve it? (Since I’m 
        such a nice guy, here’s a hint: think about the VolatileExample class 
        from the demo today).
            Not sure how that example is supposed to help. 
        
        c) argue that your solution is correct (argue, don’t prove)
            Tbh t2 takes about 10sec to complete, so couldn't we just see if that 
            thread is alive and then interrupt t3 if not? I do not understand
            how else to fix the problem with extra count?
            while(t3.isAlive())
            {
                if(t2.isAlive() == false)
                {
                    t3.interrupt();
                }        
            }     
         */
        t1.start();
        t2.start();
        t3.start();
        //Thread.sleep(10000);
        //tenCount.setIsCounting(false);
        while (t3.isAlive())
        {
            if (t2.isAlive() == false)
            {
                t3.interrupt();
            }
        }

    }
}
