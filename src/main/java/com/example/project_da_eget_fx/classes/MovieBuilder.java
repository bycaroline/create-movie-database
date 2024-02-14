package com.example.project_da_eget_fx.classes;

public class MovieBuilder {

    private String title;
    private String year;
    private String actors;
    private String director;
    private String genre;
    private String rating;

    public MovieBuilder(String title, String year, String actors, String director, String genre, String rating) {
    }

    public MovieBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieBuilder setYear(String year) {
        this.year = year;
        return this;
    }

    public MovieBuilder setActors(String actors) {
        this.actors = actors;
        return this;
    }

    public MovieBuilder setDirector(String director) {
        this.director = director;
        return this;
    }

    public MovieBuilder setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public MovieBuilder setRating(String rating) {
        this.rating = rating;
        return this;
    }

    public Movie build() {
        return new Movie(title, year, actors, director, genre, rating);
    }
}









//package com.example.project_da_eget_fx.classes;
//
//public class MovieBuilder {
//
//    private String title;
//    private String year;
//    private String actors;
//    private String director;
//    private String genre;

//    public MovieBuilder(String title, String year, String actors, String director, String genre) {
//        this.title = title;
//        this.year = year;
//        this.actors = actors;
//        this.director = director;
//        this.genre = genre;
//    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getYear() {
//        return year;
//    }
//
//    public void setYear(String year) {
//        this.year = year;
//    }
//
//
//    public String getActors() {
//        return actors;
//    }
//
//    public void setActors(String actors) {
//        this.actors = actors;
//    }
//
//    public String getDirector() {
//        return director;
//    }
//
//    public void setDirector(String director) {
//        this.director = director;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }
//
//    public Movie build() {
//        return new Movie(title, year, actors, director, genre);
//    }
//}

