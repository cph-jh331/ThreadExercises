package notdefaultpackage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private BlockingQueue<Long> calculatedValues;
    private Long totalSum;

    public Consumer(BlockingQueue<Long> calculatedValues)
    {
        this.calculatedValues = calculatedValues;
        this.totalSum = 0L;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                Long number = calculatedValues.poll(2, TimeUnit.SECONDS);
                if (number == null)
                {
                    break;
                }
                this.totalSum += number;
                System.out.println(number);

            } catch (InterruptedException ex)
            {
                System.out.println(Thread.currentThread() + " got interrupted");
            }
        }
        System.out.println("total sum of fib numbers: " + totalSum);
    }

}
