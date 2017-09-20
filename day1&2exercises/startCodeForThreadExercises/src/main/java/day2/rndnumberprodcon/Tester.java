package day2.rndnumberprodcon;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tester {

    public static void main(String[] args) throws InterruptedException
    {
        /*
        If we need a “large” collection of random numbers, what is the advantage 
        (if any) of introducing threads to “produce” the numbers?
            The threads runs at the same time, so we are able to produce
            random numbers faster than just having one thread produce 
            them all.
        
        Why does the exercise suggest 4 producer threads, and is that always 
        the right number?
            4 is the number of cores on most laptops. 
        
        Given that the Queue is a BlockingQueue implementation, how would you 
        insert data into the Queue (add(), offer(), put() ) if it’s limited in 
        capacity, and items are produced much faster than they are produced?
            If you use add(), you can/will get an exception if there is no
            space to insert.
            If you use offer(), you will get a boolean return whether it was
            successful or not.
            If you use put() the queue will wait until there is space, before
            putting the product into the queue. Which might force the threads
            to wait().
            
        
        Given that the Queue is a BlockingQueue implementation, how would you 
        fetch data from the Queue (remove(), poll(), take() ) if Production is 
        slow, compared to how we consume items
            remove() would throw an exception if there is nothing in the queue.
            poll() would return null if nothing is in the queue.
            So the real choice for a slow production is take(), since it will
            wait until for something to appear and then take it.
            
        */
        
        //This represent the Queue in the exercise-figure. Observe the size of the Queue
        ArrayBlockingQueue<Integer> numbers = new ArrayBlockingQueue(5);

        ExecutorService es = Executors.newCachedThreadPool();
        //Create and start four producers (P1-P4 in the exercise-figure)
        es.execute(new RandomNumberProducer(numbers));
        es.execute(new RandomNumberProducer(numbers));
        es.execute(new RandomNumberProducer(numbers));
        es.execute(new RandomNumberProducer(numbers));

        //Create and start single consumer (C1 in the exercise-figure)
        RandomNumberConsumer consumer = new RandomNumberConsumer(numbers);
        es.execute(consumer);

        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Total of all random numbers: " + consumer.getSumTotal());
        System.out.println("Number of random numbers below 50: " + consumer.getBelow50().size());
        System.out.println("Number of random numbers >= 50: " + consumer.getAboveOr50().size());
    }
}
