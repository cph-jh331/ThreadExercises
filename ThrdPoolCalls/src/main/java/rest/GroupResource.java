package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import producers.GroupProducer;

@Path("group")
public class GroupResource {

    @Context
    private UriInfo context;

    private GroupProducer gp;
    private static List<Group> groups;
    private static List<String> urls;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static ScheduledExecutorService scheEx = Executors.newScheduledThreadPool(1);

    public GroupResource()
    {
        if (urls == null)
        {
            urls = new ArrayList<>();
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
        }
        gp = new GroupProducer(urls);

        // This works, I just haven't figured out where to stop it:
        scheEx.scheduleAtFixedRate(() ->
        {            
            groups = gp.populateGroups();
        }, 1, 20, TimeUnit.SECONDS);
        
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroups()
    {
        if (groups == null)
        {
            try
            {
                Thread.sleep(1500);
            } catch (InterruptedException ex)
            {
                Logger.getLogger(GroupResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return Response
                .status(200)
                .entity(gson.toJson(groups))
                .build();
    }
}
