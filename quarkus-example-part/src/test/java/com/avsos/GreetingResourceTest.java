/*package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}*/

/*package org.acme;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class GreetingResourceTest {



   @Test
    public void testGetMoviesEndpoint() {
        given()
                .when().get("/movies")
                .then()
                .statusCode(200);
        // You can add additional assertions for the response body, if needed
    }

    @Test
    public void testCountMoviesEndpoint() {
        given()
                .when().get("/movies/size")
                .then()
                .statusCode(200);
        // You can add additional assertions for the response body, if needed
    }

    @Test
    public void testCreateMovieEndpoint() {
        String newMovie = "New Movie Title";
        given()
                .contentType("text/plain")
                .body(newMovie)
                .when().post("/movies")
                .then()
                .statusCode(200);
        // You can add additional assertions for the response body, if needed
    }

    @Test
    public void testUpdateMovieEndpoint() {
        String movieToUpdate = "Old Movie Title";
        String updateMovie = "Updated Movie Title";
        given()
                .pathParam("movieToUpdate", movieToUpdate)
                .queryParam("movie", updateMovie)
                .contentType("text/plain")
                .when().put("/movies/{movieToUpdate}")
                .then()
                .statusCode(200);
        // You can add additional assertions for the response body, if needed
    }

    @Test
    public void testDeleteMovieEndpoint() {
        String movieToDelete = "Movie Title to Delete";
        given()
                .pathParam("movieToDelete", movieToDelete)
                .contentType("text/plain")
                .when().delete("/movies/{movieToDelete}")
                .then()
                .statusCode(204);
        // You can add additional assertions for the response body, if needed
    }
}*/


/*package org.acme;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testGetAllMovies() {
        given()
                .when().get("/movies")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    @Test
    public void testGetMovieById() {
        Long movieId = 1L; // değiştir
        given()
                .pathParam("id", movieId)
                .when().get("/movies/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(movieId.intValue()));
    }

    @Test
    public void testGetNonExistingMovieById() {
        Long nonExistingId = 100L; // değiştir
        given()
                .pathParam("id", nonExistingId)
                .when().get("/movies/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testCreateMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDescription("Test Description");
        movie.setDirector("Test Director");
        movie.setCountry("Test Country");

        given()
                .contentType(ContentType.JSON)
                .body(movie)
                .when().post("/movies")
                .then()
                .statusCode(201);
    }

    @Test
    public void testDeleteMovie() {
        Long movieIdToDelete = 1L; // değiştir

        given()
                .pathParam("id", movieIdToDelete)
                .when().delete("/movies/{id}")
                .then()
                .statusCode(204);
    }
}*/

/*package org.acme;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GreetingResourceTest {

    @Inject
    MovieService movieService;

    @BeforeEach
    @Transactional
    public void setup() {

    }

    @Test
    @Transactional
    public void testMovieResourceEndpoints() {
        // Create a new movie
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setReleaseYear(2023);
        movie.setDirector("Test Director");

        given()
                .contentType(ContentType.JSON)
                .body(movie)
                .when()
                .post("/movies")
                .then()
                .statusCode(204);

        // Retrieve the created movie
        Long movieId = movie.getId();
        given()
                .when()
                .get("/movies/{id}", movieId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("Test Movie"))
                .body("releaseYear", equalTo(2023))
                .body("director", equalTo("Test Director"));

        // Update the movie
        movie.setTitle("Updated Movie Title");
        given()
                .contentType(ContentType.JSON)
                .body(movie)
                .when()
                .put("/movies/{id}", movieId)
                .then()
                .statusCode(204);

        // Verify the movie was updated
        given()
                .when()
                .get("/movies/{id}", movieId)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("Updated Movie Title"));

        // Delete the movie
        given()
                .when()
                .delete("/movies/{id}", movieId)
                .then()
                .statusCode(204);

        // Verify the movie was deleted
        given()
                .when()
                .get("/movies/{id}", movieId)
                .then()
                .statusCode(404);
    }

    @Test
    @Transactional
    public void testMovieServiceCRUD() {
        // Test MovieService CRUD operations
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setReleaseYear(2023);
        movie.setDirector("Test Director");

        // Create
        movieService.createMovie(movie);
        Long movieId = movie.getId();
        Assertions.assertNotNull(movieId, "Movie ID should not be null after creation");

        // Read
        Movie fetchedMovie = movieService.getMovieById(movieId);
        Assertions.assertNotNull(fetchedMovie, "Fetched movie should not be null");
        Assertions.assertEquals(movieId, fetchedMovie.getId(), "Fetched movie ID should match the original movie ID");

        // Update
        fetchedMovie.setTitle("Updated Movie Title");
        movieService.updateMovie(fetchedMovie);
        Movie updatedMovie = movieService.getMovieById(movieId);
        Assertions.assertEquals("Updated Movie Title", updatedMovie.getTitle(), "Movie title should be updated");

        // Delete
        movieService.deleteMovie(movieId);
        Movie deletedMovie = movieService.getMovieById(movieId);
        Assertions.assertNull(deletedMovie, "Deleted movie should be null");
    }

    @Test
    @Transactional
    public void testGetAllMovies() {
        Movie movie1 = new Movie();
        movie1.setTitle("Test Movie 1");
        movie1.setReleaseYear(2023);
        movie1.setDirector("Test Director 1");
        movieService.createMovie(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("Test Movie 2");
        movie2.setReleaseYear(2023);
        movie2.setDirector("Test Director 2");
        movieService.createMovie(movie2);

        List<Movie> movies = movieService.getAllMovies();
        Assertions.assertEquals(2, movies.size(), "There should be 2 movies in the list");
    }
}*/

