package day1.e1;

public class SumNumber implements Runnable {

    private long sum = 0;

    @Override
    public void run()
    {

        for (long i = 1; i <= 1000000000L; i++)
        {
            this.sum += i;
        }
        System.out.println(this.sum);
    }

}
