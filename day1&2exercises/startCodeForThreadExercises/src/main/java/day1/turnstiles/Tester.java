package day1.turnstiles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tester {

    static final int NUMBER_OF_TURNSTILES = 40;
    static Turnstile[] turnStiles = new Turnstile[NUMBER_OF_TURNSTILES];

    public static void main(String[] args) throws InterruptedException
    {
        //This is the shared Counter used by all turnstilles
        TurnstileCounter sharedCounter = new TurnstileCounter();

        for (int i = 0; i < NUMBER_OF_TURNSTILES; i++)
        {
            turnStiles[i] = new Turnstile(sharedCounter);
        }

        //This example uses a ThreadPool to handle threads
        ExecutorService es = Executors.newCachedThreadPool();

        for (int i = 0; i < NUMBER_OF_TURNSTILES; i++)
        {
            es.execute(turnStiles[i]);
        }

        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("All turnstiles are done");
        //Print the updated value
        System.out.println(sharedCounter.getValue());

        /*
        a) Do you get the right result? 
            Depends if the number of spectators are supposed to be random and
            less than 40000...
            But I guess not.
        
        b) What is the problem in the code? (It’s there whether you get the 
        right result or not)
            The problem is that all the threads are changing the value of the
            the shared counter, making some of the spectators not be counted.
            
            
        c) Solve the problem using synchronization techniques.
            Can be fixed by making TurnTileCounter.incr() synchronized.            
         */
    }
}
