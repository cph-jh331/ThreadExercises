package day2.webscraper;

public class Tester {

    public static void main(String[] args) throws InterruptedException
    {
        TagCounter tc1 = new TagCounter("http://www.fck.dk");
        TagCounter tc2 = new TagCounter("http://www.google.com");
        TagCounter tc3 = new TagCounter("http://politiken.dk/");

        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());
        Thread thread1 = new Thread(tc1);
        Thread thread2 = new Thread(tc2);
        Thread thread3 = new Thread(tc3);
//        long start = System.nanoTime();
//        tc1.run();
//        tc2.run();
//        tc3.run();
//        long end = System.nanoTime();
//        System.out.println("Time Sequential: " + (end - start));

        // What is a smart way to check if all the threads are dead?
        // but parallel is faster.
        long start = System.nanoTime();
        thread1.start();
        thread2.start();
        thread3.start();
        long end = System.nanoTime();
        System.out.println("Time Sequential: " + (end - start));

    }
}
