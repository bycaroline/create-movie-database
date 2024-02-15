/**
 * Class for creating MovieData objects that do not have a rating.
 * The class is used to create objects on the first search of a movie.
 */

package com.example.project_da_eget_fx.classes;

public class MovieData {
    private String title;
    private String year;
    private String actors;
    private String genre;
    private String director;

    public MovieData(String title, String year, String actors, String genre, String director) {
        this.title = title;
        this.year = year;
        this.actors = actors;
        this.genre = genre;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getActors() {
        return actors;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }
}
