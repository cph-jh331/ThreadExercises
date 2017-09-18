package day1.e1;

public class TenCount implements Runnable {

    private volatile boolean isCounting = true;
    private volatile long count = 10;

    public void setIsCounting(boolean isCounting)
    {
        this.isCounting = isCounting;
    }

    @Override
    public void run()
    {
        while (isCounting)
        {
            System.out.println("hvad fanden");
            try
            {
                Thread.sleep(3000);
                if (isCounting == false)
                {
                    return;
                }
                System.out.println(count);

            } catch (InterruptedException e)
            {
                System.out.println(Thread.currentThread().getName() + " Interrupted!");
                return;
            }
            count++;
        }
    }

}
