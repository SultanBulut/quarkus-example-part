package org.acme;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieController {

    @Inject
    MovieService movieService;

    @GET
    public Response getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return Response.ok(movies).build();
    }

    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") Long id){
        Movie movie = movieService.getMovieById(id);
        if(movie!=null){
            return Response.ok(movie).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createMovie(MovieDTO movieDTO){
        if(movieService.createMovie(movieDTO)){
            return Response.ok().build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    public Response updateMovie(Movie movie){
        movieService.updateMovie(movie);
        return Response.ok(movie).build();
    }

    @DELETE
    @Path("/{title}")
    public Response deleteMovie(@PathParam("title")String title){
        movieService.deleteMovie(title);
        return Response.noContent().build();

    }
}

