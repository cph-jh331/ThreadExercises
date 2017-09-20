package day2.webscrapprodcon;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DocumentConsumer implements Runnable {

    BlockingQueue<Document> producedDocuments;
    Document doc;

    public DocumentConsumer(BlockingQueue<Document> producedDocuments)
    {
        this.producedDocuments = producedDocuments;
    }

    @Override
    public void run()
    {
        boolean moreDocumentsToConsume = true;
        Document doc;
        int totalDivs = 0;
        while (moreDocumentsToConsume)
        {
            try
            {
                doc = producedDocuments.poll(10, TimeUnit.SECONDS);
                if (doc != null)
                {
                    String title = doc.title();
                    Elements divs = doc.select("div");

                    //TODO Update the totalDivs variable and print title and sum for this document
                    totalDivs += divs.size();
                    System.out.println(title + " " + totalDivs);
                } else
                {
                    moreDocumentsToConsume = false;
                }

            } catch (Exception ex)
            {
                Logger.getLogger(DocumentConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Sum of all Divs:" + totalDivs);
    }

}
