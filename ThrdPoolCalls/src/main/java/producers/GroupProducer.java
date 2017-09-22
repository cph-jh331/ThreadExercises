package producers;

import entity.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GroupProducer {

    private BlockingQueue<String> urlQueue;
    

    public GroupProducer(List<String> urls)
    {
        urlQueue = new ArrayBlockingQueue(urls.size());
        for (String url : urls)
        {
            urlQueue.add(url);
        }
    }

    public List<Group> populateGroups()
    {
        System.out.println("updating groups");
        List<Group> groups = new ArrayList<>();
        List<Future<Group>> futureList = new ArrayList<>();
        ExecutorService ex = Executors.newFixedThreadPool(10);
        for (String url : urlQueue)
        {
            
            Callable<Group> ca = () ->
            {
                Document doc = Jsoup.connect(url).get();
                String authors = doc.select("#authors").text();
                String klasse = doc.select("#class").text();
                String group = doc.select("#group").text();
                return new Group(authors, klasse, group);
            };

            Future<Group> fu = ex.submit(ca);
            futureList.add(fu);
        }
        for (Future<Group> future : futureList)
        {
            try
            {
                groups.add(future.get());
            } catch (InterruptedException | ExecutionException ex1)
            {
                System.out.println("hvad satan");
                break;
            }
        }
        ex.shutdown();
        try
        {
            ex.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException ex1)
        {
            System.out.println("Interrupted... why?");
        }
        return groups;
    }

}
