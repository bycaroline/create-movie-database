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
    //private static InputHelper inputHelper = new InputHelper();
    Database database = new Database();
    MoviesDAO moviesDAO = new MoviesDAO(database);

    private String year;
    private String actors;
    private String director;
    private String genre;


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


    @FXML
    private void searchByTitle() {

        //kolla om namn och år redan finns. I så fall skriv ut det.Annars sök efter ny.
        String title = textfieldTitle.getText();

        if (title.trim().isEmpty()) {
            emptyField();
            return;
        }

        if (notInDatabase(title)) {
            System.out.println("Movie in database");
        } else {
            getDataFromApi(title);
            searchResultTextArea.setText("Title: " + title
                    + "\n" +
                    "Director: " + director
                    + "\n" +
                    "Year: " + year
                    + "\n" +
                    "Actors: " + actors
                    + "\n" +
                    "Genre: " + genre
            );
        }
    }

    private MovieData getDataFromApi(String title) {
        MovieData movieData = apiService.getDataByTitle(title);
        year = movieData.getYear();
        actors = movieData.getActors();
        director = movieData.getDirector();
        genre = movieData.getGenre();

        return new MovieData(title, year, actors, genre, director);
    }

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

    @FXML
    private String setRating() {
        String rating = textFieldGiveRating.getText();

        if (rating.trim().isEmpty() || !rating.matches("[0-5]")) {
            emptyRating();
        }
        return rating;
    }

    @FXML
    private void setRatingAndSave() {
        String rating = setRating();
        String title = textfieldTitle.getText();

        MovieData movieData = getDataFromApi(title);
        year = movieData.getYear();
        actors = movieData.getActors();
        director = movieData.getDirector();
        genre = movieData.getGenre();

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
        confirmSave.setText("Movie" + title + "saved to database");
        searchResultTextArea.setText("");
    }

    @FXML
    private void searchByYear() {
        String year = searchDatabaseYear.getText();
        List<Movie> movies = moviesDAO.findMovieInDatabaseByYear(year);

        if (year.trim().isEmpty()) {
            emptyField();
            return;
        }

        boolean found = false;

        for (Movie movie : movies) {
            if (movie.getYear().contains(year)) {
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
                found = true;
            }
            searchDatabaseYear.clear();
        }

        if (!found) {
            notFound();
            System.out.println("Movie not found");
        }
    }

    @FXML
    private void searchByActor() {
        String actor = searchDatabaseActor.getText();
        List<Movie> movies = moviesDAO.findMovieInDatabaseByActor(actor);

        if (actor.trim().isEmpty()) {
            emptyField();
            return;
        }

        boolean found = false;

        for (Movie movie : movies) {
            if (movie.getActors().toLowerCase().contains(actor.toLowerCase())) {
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
                found = true;
            }
            searchDatabaseActor.clear();
        }

        if (!found) {
            notFound();
            System.out.println("Movie not found");
        }
    }

    @FXML
    private void searchByDirector() {
        String director = searchDatabaseDirector.getText();
        List<Movie> movies = moviesDAO.findMovieInDatabaseByDirector(director);

        if (director.trim().isEmpty()) {
            emptyField();
            return;
        }

        boolean found = false;

        for (Movie movie : movies) {
            if (movie.getDirector().contains(director)) {
                System.out.println("Movie found: " + movie.getTitle());
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
                found = true;
            }
            searchDatabaseDirector.clear();
        }

        if (!found) {
            notFound();
            System.out.println("Movie not found");
        }
    }

    @FXML
    private void searchByRating() {
        String rating = searchDatabaseDirector.getText();
        List<Movie> movies = moviesDAO.findMovieInDatabaseByRating(rating);

        if (rating.trim().isEmpty()) {
            emptyField();
            return;
        }

        boolean found = false;

        for (Movie movie : movies) {
            if (movie.getDirector().contains(rating)) {
                System.out.println("Movie found: " + movie.getTitle());
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
                found = true;
            }
        }

        if (!found) {
            notFound();
            System.out.println("Movie not found");
        }
        searchDatabaseRating.clear();
    }


//    private void viewDetailsMovie(Movie movie) {
//        searchResultTextArea.setText("Title: " + movie.getTitle()
//                + "\n" +
//                "Director: " + movie.getDirector()
//                + "\n" +
//                "Year: " + movie.getYear()
//                + "\n" +
//                "Actors: " + movie.getActors()
//                + "\n" +
//                "Genre: " + movie.getGenre()
//                + "\n" +
//                "Rating: " + movie.getRating()
//        );
//    }

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
