package day1.e2;

public class Exercise2RaceCondition {

    public static void main(String[] args)
    {
        Even even = new Even();
        Runnable r = () ->
        {
            for (int i = 0; i < 10; i++)
            {
                System.out.println(Thread.currentThread().getName()
                        + " " + even.next());
            }
        };

        /*
        a) Are you able to provoke the expected error on your machine?
            Yes.
        
        b) How often does the problem occur?
            At random. If you are unlucky you will never see it. The problem is
            that one of the threads sneaks in and changes the value of n, while
            the other thread is also calling it, and incrementing the value of
            n to be not even.
        
        c) Use the synchronization techniques you’ve learned today, to make 
        next() method atomic with respect to itself.
            Ok. Synchronize the method.
        
        d) Argue that your solution is correct (argue, don’t prove)
            The reason i put the even.next() in a forloop is just to test, 
            that using synchronized on a method does not lock the entire 
            Even object...
            Using synchronized on a method locks the method from use from
            other threads until the current thread's call to the method has 
            run its course.
         */
        Thread t1 = new Thread(r, "thread 1");
        Thread t2 = new Thread(r, "thread 2");

        t1.start();
        t2.start();
    }

}
