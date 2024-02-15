/**
 * This class manages the interaction between the user interface and the underlying data.
 */
package com.example.project_da_eget_fx;

import com.example.project_da_eget_fx.api.ApiService;
import com.example.project_da_eget_fx.classes.Movie;
import com.example.project_da_eget_fx.classes.MovieBuilder;
import com.example.project_da_eget_fx.classes.MovieData;
import com.example.project_da_eget_fx.dao.MoviesDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {
    private ApiService apiService = new ApiService();
    Database database = new Database();
    MoviesDAO moviesDAO = new MoviesDAO(database);


    /**
     * The fields for the movie details.
     */
    private String year;
    private String actors;
    private String director;
    private String genre;

    /**
     * Enum representing different search criteria.
     */
    public enum Criteria {
        YEAR,
        ACTOR,
        DIRECTOR,
        RATING
    }

    @FXML
    private TextArea searchResultTextArea;
    @FXML
    private TextField textfieldTitle;
    @FXML
    private TextField searchDatabaseYear;
    @FXML
    private TextField searchDatabaseActor;
    @FXML
    private TextField searchDatabaseDirector;
    @FXML
    private TextField searchDatabaseRating;
    @FXML
    private TextField textFieldGiveRating;
    @FXML
    private Label confirmSave;

    /**
     * Searches for a movie by title and wheter or not it is already in the database.
     */
    @FXML
    private void searchByTitle() {
        String title = textfieldTitle.getText();

        if (title.trim().isEmpty()) {
            emptyField();
            return;
        }

        if (notInDatabase(title)) {
            searchResultTextArea.setText("Movie already in database");
        } else {
            try{
                getDataFromApi(title);
                searchResultTextArea.setText("Title: " + title
                        + "\n" +
                        "Director: " + director
                        + "\n" +
                        "Year: " + year
                        + "\n" +
                        "Actors: " + actors
                );
            } catch (RuntimeException e) {
                notFound();
                return;
            }
        }
    }

    /**
     * fetches data from the API and returns Object MovieData that does not contain rating.
     */
    private MovieData getDataFromApi(String title) {
        MovieData movieData = apiService.getDataByTitle(title);
        year = movieData.getYear();
        actors = movieData.getActors();
        director = movieData.getDirector();
        genre = movieData.getGenre();

        return new MovieData(title, year, actors, genre, director);
    }

    /**
     * Checks if the movie is in the database.
     */
    private boolean notInDatabase(String title) {
        List<Movie> movies = moviesDAO.findMovieInDatabaseByTitle(title);

        if (!movies.isEmpty()) {
            for (Movie movie : movies) {
                searchResultTextArea.setText("Title: " + movie.getTitle()
                + "\n" +
                "Director: " + movie.getDirector()
                + "\n" +
                "Year: " + movie.getYear()
                + "\n" +
                "Actors: " + movie.getActors()
                + "\n" +
                "Rating: " + movie.getRating()
        );
            }
            return true;
        }
        return false;
    }

    /**
     * Sets the rating for the movie and returns it
     */
    @FXML
    private String setRating() {
        String rating = textFieldGiveRating.getText();
        if (rating.trim().isEmpty() || !rating.matches("[0-5]")) {
            emptyRating();
            return null;
        }
        return rating;
    }

    /**
     * Sets the rating and saves the movie to the database.
     */
    @FXML
    private void setRatingAndSave() {
        String title = textfieldTitle.getText();
        MovieData movieData = getDataFromApi(title);
        year = movieData.getYear();
        actors = movieData.getActors();
        director = movieData.getDirector();
        genre = movieData.getGenre();
        String rating = setRating();
        if (rating == null) {
            return;
        }

        MovieBuilder movieBuilder = new MovieBuilder(title, year, actors, director, genre, rating);
        movieBuilder.setTitle(title)
                .setYear(year)
                .setActors(actors)
                .setDirector(director)
                .setGenre(genre)
                .setRating(rating);

        Movie newMovie = movieBuilder.build();
        moviesDAO.addMovieToDatabase(newMovie);
        textFieldGiveRating.clear();
        confirmSave.setText("Movie " + title + " saved to database");
        searchResultTextArea.setText("");
    }

    /**
     * Checks which textfield is not empty and searches for the movie by that criteria.
     */
    @FXML
    private void search() {
        String year = searchDatabaseYear.getText();
        String actor = searchDatabaseActor.getText();
        String director = searchDatabaseDirector.getText();
        String rating = searchDatabaseRating.getText();

        if (!year.isEmpty()) {
            searchByCriteria(year, Criteria.YEAR);
        } else if (!actor.isEmpty()) {
            searchByCriteria(actor, Criteria.ACTOR);
        } else if (!director.isEmpty()) {
            searchByCriteria(director, Criteria.DIRECTOR);
        } else if (!rating.isEmpty()) {
            searchByCriteria(rating, Criteria.RATING);
        } else {
            emptyField();
        }
    }

    /**
     * Searches for a movie by different criteria.
     */
    private void searchByCriteria(String criteria, Criteria type) {
        List<Movie> movies = null;
        switch (type) {
            case YEAR:
                movies = moviesDAO.findMovieInDatabaseByYear(criteria);
                break;
            case ACTOR:
                movies = moviesDAO.findMovieInDatabaseByActor(criteria);
                break;
            case DIRECTOR:
                movies = moviesDAO.findMovieInDatabaseByDirector(criteria);
                break;
            case RATING:
                movies = moviesDAO.findMovieInDatabaseByRating(criteria);
                break;
        }

        if (movies.isEmpty()) {
            notFound();
        } else {
            boolean found = false;
            StringBuilder results = new StringBuilder();
            for (Movie movie : movies) {
                viewDetailsMovie(movie, results);
                found = true;
            }
            searchResultTextArea.setText(results.toString());
            if (!found) {
                notFound();
            }
        }
        clearFields();
        confirmSave.setText("");
    }

    /**
     * Sets the prompt text for the textfields back to original.
     */
    private void clearFields() {
        searchDatabaseYear.clear();
        searchDatabaseActor.clear();
        searchDatabaseDirector.clear();
        searchDatabaseRating.clear();
    }

    /**
     * Appends the details of the movie to the results.
     */
    private void viewDetailsMovie(Movie movie, StringBuilder results) {
        results.append("Title: ").append(movie.getTitle()).append("\n")
                .append("Director: ").append(movie.getDirector()).append("\n")
                .append("Year: ").append(movie.getYear()).append("\n")
                .append("Actors: ").append(movie.getActors()).append("\n")
                .append("Rating: ").append(movie.getRating()).append("\n");
    }

    /**
     * Alert methods for different scenarios.
     */
    private void emptyField() {
        Alert alertEmpty = new Alert(Alert.AlertType.INFORMATION);
        alertEmpty.setTitle("Empty field");
        alertEmpty.setHeaderText("Search field was empty");
        alertEmpty.setContentText("Write something to search for");
        alertEmpty.showAndWait();
    }

    private void emptyRating() {
        Alert alertEmpty = new Alert(Alert.AlertType.INFORMATION);
        alertEmpty.setTitle("Empty field");
        alertEmpty.setHeaderText("Rating was not added");
        alertEmpty.setContentText("Add a rating between 0-5 for the movie");
        alertEmpty.showAndWait();
    }

    private void notFound() {
        Alert alertNotFound = new Alert(Alert.AlertType.INFORMATION);
        alertNotFound.setTitle("Not found");
        alertNotFound.setHeaderText("Movie was not found");
        alertNotFound.setContentText("Try to search for another movie");
        alertNotFound.showAndWait();
    }
}
