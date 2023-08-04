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

    @Inject
    MovieProducer movieProducer;

    @GET
    public Response getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return Response.ok(movies).build();
    }

    @GET
    @Path("/{title}")
    public Response getMovieByTitle(@PathParam("title") String title){
        Movie movie = movieService.getMovieByTitle(title);
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
    @Path("/{title}")
    public Response updateMovie(@PathParam("title") String title, MovieDTO movieDto){
        movieService.updateMovie(title,movieDto);
        return Response.ok(movieDto).build();
    }

    @DELETE
    @Path("/{title}")
    public Response deleteMovie(@PathParam("title")String title){
        movieService.deleteMovie(title);
        return Response.noContent().build();

    }
    // New method for sending movies to Kafka
    @POST
    @Path("/kafka")
    public Response sendToKafka(Movie movie) {
        movieProducer.sendMovieToKafka(movie);
        // Return a 202 - Accepted response.
        return Response.accepted().build();
    }
}

