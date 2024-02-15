/**
 * Class for the Movie object
 */

package com.example.project_da_eget_fx.classes;

public class Movie {
    private String title;
    private String year;
    private String actors;
    private String director;
    private String genre;
    private String rating;

    public Movie(String title, String year, String actors, String director, String genre, String rating) {
        this.title = title;
        this.year = year;
        this.actors = actors;
        this.director = director;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String toString() {
        return "Movie{" +
                "Title='" + title + '\'' +
                ", Year='" + year + '\'' +
                ", Actors='" + actors + '\'' +
                ", Director='" + director + '\'' +
                ", Genre='" + genre + '\'' +
                ", Rating='" + rating + '\'' +
                '}';
    }
}



