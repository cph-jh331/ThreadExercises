package notdefaultpackage;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<Long> startNumbers;
    private BlockingQueue<Long> calculatedValues;

    public Producer(BlockingQueue<Long> startNumbers, BlockingQueue<Long> calculatedValues)
    {
        this.startNumbers = startNumbers;
        this.calculatedValues = calculatedValues;
    }

    private long fib(long n)
    {
        if ((n == 0) || (n == 1))
        {
            return n;
        } else
        {
            return fib(n - 1) + fib(n - 2);
        }
    }

    @Override
    public void run()
    {
        boolean isNumbersEmpty = false;
        while (!isNumbersEmpty)
        {
            Long number = startNumbers.poll();
            if (number == null)
            {
                isNumbersEmpty = true;
                break;
            }
            Long calculatedNumber = fib(number);
            try
            {
                calculatedValues.put(calculatedNumber);
            } catch (InterruptedException ex)
            {
                System.out.println(Thread.currentThread() + " " + " got interrupted.");
            }
        }
    }

}
