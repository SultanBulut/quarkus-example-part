/*package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}*/


/*package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import liquibase.change.CheckSum;
import liquibase.changelog.ChangeSetStatus;



@Path("/movies")
public class GreetingResource{

    public static List <String> movies =new ArrayList<>();
    //public static List <Movie> movies =new ArrayList<>();

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMovies(){
        return Response.ok(movies).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    public Integer countMovies(){
        return movies.size();
    }

   @POST
    @Produces(MediaType.TEXT_PLAIN) 
    @Consumes(MediaType.TEXT_PLAIN)
     // @Produces(MediaType.APPLICATION_JSON) 
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response createMovie(String newMovie){  // Movie newMovie
        movies.add(newMovie);
        //movies.add(newMovie);
        return Response.ok(movies).build();
    }

    
    @PUT
    @Path("{movieToUpdate}")
    @Produces(MediaType.TEXT_PLAIN) 
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateMovie(
        @PathParam("movieToUpdate") String movieToUpdate,
        @QueryParam("movie") String updateMovie){
        movies= movies.stream().map(movie->{
            if(movie.equals(movieToUpdate)){
                return updateMovie;
            }
            return movie;
        }).collect(Collectors.toList());
        return Response.ok(movies).build();

    }

    @DELETE
    @Path("{movieToDelete}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteMovie(
            @PathParam("movieToDelete") String movieToDelete){
        boolean removed = movies.remove(movieToDelete);
        return removed ? Response.noContent().build():
               Response.status(Response.Status.BAD_REQUEST).build();
            }

}*/
/*import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.Users;
import org.acme.UserRepository;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource{

    @Inject
    UserRepository userRepository;

    @POST
    public void createUser(Users users) {
        userRepository.createUser(users);
    }

    @GET
    public List<Users> getAllUsers() {
        return userRepository.getAllUsers();
    }
}*/

/*package org.acme;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import org.acme.MovieService;
import org.acme.Movie;
import java.util.List;

@Path("/movies")
public class GreetingResource {

    @Inject
    private MovieService movieService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Movie> movies = movieService.getAllMovies();
        return Response.ok(movies).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return Response.ok(movie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("country/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCountry(@PathParam("country") String country) {
        List<Movie> movies = movieService.getMoviesByCountry(country);
        return Response.ok(movies).build();
    }

    @GET
    @Path("/title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTitle(@PathParam("title") String title) {
        Movie movie = movieService.getMovieByTitle(title);
        if (movie != null) {
            return Response.ok(movie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Movie movie) {
        movieService.createMovie(movie);
        if (movie.getId() != null) {
            return Response.created(URI.create("/movies/" + movie.getId())).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = movieService.deleteMovieById(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}*/
package org.acme;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    MovieService movieService;

    @GET
    public Response getAllMovies(){
        List<Movie> movies =movieService.getAllMovies();
        return Response.ok(movies).build();
    }
    //public List<Movie> getAllMovies() {
    //    return movieService.getAllMovies();
    //}

    @GET
    @Path("/{id}")
    public Response getMovieById(@PathParam("id") Long id){
        Movie movie =movieService.getMovieById(id);
        if(movie!=null){
            return Response.ok(movie).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    // public Movie getMovieById(@PathParam("id") Long id) {
    //    return movieService.getMovieById(id);
    //}

    @POST
    public Response createMovie(Movie movie){
        movieService.createMovie(movie);
        if(movie.getId()!=null){
            return Response.created(URI.create("/movies/"+movie.getId())).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
   // public void createMovie(Movie movie) {
    //    movieService.createMovie(movie);
    //}

    @PUT
    @Path("/{id}")
    public Response updateMovie(@PathParam("id") Long id,Movie movie){
        movie.setId(id);
        movieService.updateMovie(movie);
        return Response.ok(movie).build();
    }
    //public void updateMovie(@PathParam("id") Long id, Movie movie) {
     //   movie.setId(id);
    //    movieService.updateMovie(movie);
    //}

    @DELETE
    @Path("/{id}")
    public Response deleteMovie(@PathParam("id")Long id){
        movieService.deleteMovie(id);
        return Response.noContent().build();

    }
    //public void deleteMovie(@PathParam("id") Long id) {
     //   movieService.deleteMovie(id);
    //}
}

