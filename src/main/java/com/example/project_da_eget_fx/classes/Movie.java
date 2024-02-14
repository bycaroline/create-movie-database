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

//    // Metod för att skriva ut filmens detaljer
//    public void printMovie() {
//        System.out.println("Title: " + title);
//        System.out.println("Year: " + year);
//        System.out.println("Actors: " + actors);
//        System.out.println("Director: " + director);
//        System.out.println("Genre: " + genre);
//        System.out.println("Rating: " + rating);
//    }

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



