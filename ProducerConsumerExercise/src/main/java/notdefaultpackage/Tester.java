package notdefaultpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tester {

    public static void main(String[] args) throws InterruptedException
    {
        calcFibsWithNumbThreads(8);
    }

    public static void calcFibsWithNumbThreads(int numberOfProducers) throws InterruptedException
    {
        Collection<Long> nList = new ArrayList<>();
        //4,5,8,12,21,22,34,35,36,37,42
        nList.add(4L);
        nList.add(5L);
        nList.add(8L);
        nList.add(12L);
        nList.add(21L);
        nList.add(22L);
        nList.add(34L);
        nList.add(35L);
        nList.add(36L);
        nList.add(37L);
        nList.add(42L);

        BlockingQueue<Long> startNumbers = new ArrayBlockingQueue(nList.size());
        for (Long number : nList)
        {
            startNumbers.add(number);
        }

        BlockingQueue<Long> calculatedNumbers = new ArrayBlockingQueue(10);
        ExecutorService ex = Executors.newCachedThreadPool();
        for (int i = 0; i < numberOfProducers; i++)
        {
            ex.execute(new Producer(startNumbers, calculatedNumbers));
        }
        ex.execute(new Consumer(calculatedNumbers));
        ex.shutdown();
        ex.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("Closing down...");
    }

}
