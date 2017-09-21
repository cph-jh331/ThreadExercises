package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Group;
import java.util.ArrayList;
import java.util.List;
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

    public GroupResource()
    {
        gp = new GroupProducer();
        if (urls == null)
        {
            urls = new ArrayList<>();
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
            urls.add("http://46.101.216.31/CA1-Group13/");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroups()
    {
        if (groups == null)
        {
            groups = gp.getGroups(urls);
        }

        return Response
                .status(200)
                .entity(gson.toJson(groups))
                .build();

    }
}
