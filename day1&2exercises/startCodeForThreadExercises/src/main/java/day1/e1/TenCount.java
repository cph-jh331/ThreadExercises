package day1.e1;

public class TenCount implements Runnable {

    private boolean isCounting = true;
    private long count = 9;

    public void setIsCounting(boolean isCounting)
    {
        this.isCounting = isCounting;
    }

    @Override
    public void run()
    {
        while (isCounting)
        {
            count++;
            try
            {
                Thread.sleep(3000);
                System.out.println(count);

            } catch (InterruptedException e)
            {
                System.out.println(Thread.currentThread().getName() + " Interrupted!");
                return;
            }
        }
    }

}
