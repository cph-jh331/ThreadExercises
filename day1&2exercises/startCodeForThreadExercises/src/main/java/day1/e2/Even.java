package day1.e2;

public class Even {

    private int n = 0;

    public synchronized int next()
    {
        this.n++;
        this.n++;
        return this.n;
    }

}
