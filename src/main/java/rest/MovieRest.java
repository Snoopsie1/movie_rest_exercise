package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieRest
{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final MovieFacade FACADE =  MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Path("demo")
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("count")
    @Produces("application/json")
    public String getRenameMeCount() {
        long count = FACADE.getMovieCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllMovies() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllMovies())).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) throws EntityNotFoundException
    {
        MovieDTO p = FACADE.getById(id);
        return Response.ok().entity(GSON.toJson(p)).build();
    }

    @GET
    @Path("title/{title}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByTitle(@PathParam("title") String title) throws EntityNotFoundException
    {
        MovieDTO p = FACADE.getMovieByTitle(title);
        return Response.ok().entity(GSON.toJson(p)).build();
    }


}
