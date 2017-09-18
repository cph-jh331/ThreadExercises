package day1.e1;

public class FiveCount implements Runnable {

    @Override
    public void run()
    {
        for (int i = 1; i <= 5; i++)
        {
            try
            {
                Thread.sleep(2000);
                System.out.println(i);
            } catch (InterruptedException ex)
            {
                System.out.println("interrupted.");
            }

        }
    }

}
