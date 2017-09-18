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
            Depends if the number of spectators are supposed to be random...
            But I guess not.
        
        b) What is the problem in the code? (It’s there whether you get the 
        right result or not)
            The problem is that every one is calling the same sharedCounter.
            So the threads are sneaking in and doing something with value 
            making some of the spectators not be counted... Why is this? 
            I can fix it by synchronizing the incr() method, but I do not 
            understand why the number of spectators becomes less, when not 
            synchronized.
            So the solution might be wrong, even if it works.
        ***********************************************************************
            Is it because of the shutdown() that just stops the adding of new 
            tasks?
            So all the calls to the incr method gets stored as tasks before 
            the main thread gets to call shutdown()?
            
            
        c) Solve the problem using synchronization techniques.
            Can be fixed by making TurnTileCounter.incr() synchronized.
            
         */
    }
}
