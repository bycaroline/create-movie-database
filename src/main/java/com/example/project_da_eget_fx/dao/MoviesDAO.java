package com.example.project_da_eget_fx.dao;

import com.mysql.cj.protocol.Resultset;
import com.example.project_da_eget_fx.Database;
import com.example.project_da_eget_fx.classes.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoviesDAO {
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS ";
    private final Database database;

    public MoviesDAO(Database database) {
        this.database = database;
        initializeTable();
        clearMoviesTable();
    }

    private void initializeTable()  {
        Connection connection = database.getConnection();

        String tableName = "movies";
//        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
//                "id INT AUTO_INCREMENT PRIMARY KEY, " +
//                "title VARCHAR(100), " +
//                "year VARCHAR(100), " +
//                "actors VARCHAR(255), " +
//                "director VARCHAR(100), " +
//                "genre VARCHAR(255)," +
//                "rating VARCHAR(100))";

        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(100), " +
                "year VARCHAR(4), " +
                "actors VARCHAR(255), " +
                "director VARCHAR(100), " +
                "genre VARCHAR(255), " +
                "rating VARCHAR(10))";

        Statement st = null;
        try {
            st = connection.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Skapa prepared Statements
    private static final String DELETE_ALL_MOVIES_SQL = "DELETE FROM movies";
    private static final String INSERT_MOVIE_SQL = "INSERT INTO movies (title, year, actors, director, genre, rating) VALUES (?,?,?,?,?,?)";
    private static final String SELECT_MOVIE_BY_TITLE_SQL = "SELECT * FROM movies WHERE title LIKE ?";
    private static final String SELECT_MOVIE_BY_ACTOR_SQL = "SELECT * FROM movies WHERE actors LIKE ?";
    private static final String SELECT_MOVIE_BY_YEAR_SQL = "SELECT * FROM movies WHERE year = ?";
    private static final String SELECT_MOVIE_BY_DIRECTOR_SQL = "SELECT * FROM movies WHERE director = ?";
    private static final String SELECT_MOVIE_BY_RATING = "SELECT * FROM movies WHERE rating = ?";

    // Metoder för att hantera databasoperationer

    public void clearMoviesTable() {
        Connection connection = database.getConnection();
        try (PreparedStatement st = connection.prepareStatement(DELETE_ALL_MOVIES_SQL)) {
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMovieToDatabase(Movie movie) {

        Connection connection = database.getConnection();
        try (PreparedStatement st = connection.prepareStatement(INSERT_MOVIE_SQL)) {
            st.setString(1, movie.getTitle());
            st.setString(2, movie.getYear());
            st.setString(3, movie.getActors());
            st.setString(4, movie.getDirector());
            st.setString(5, movie.getGenre());
            st.setString(6, movie.getRating());
            st.executeUpdate();
            System.out.println("Movie added to database");
        } catch (SQLException e) {
            System.out.println("error adding movie to db");
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findMovieInDatabaseByTitle(String search) {
        List<Movie> movies = new ArrayList<>();
        Connection connection = database.getConnection();

        try (PreparedStatement st = connection.prepareStatement(SELECT_MOVIE_BY_TITLE_SQL)) {
            st.setString(1, search);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                String actors = resultSet.getString("actors");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String rating = resultSet.getString("rating");

                movies.add(new Movie(title,year,actors,director,genre, rating));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findMovieInDatabaseByActor(String search) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = database.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SELECT_MOVIE_BY_ACTOR_SQL)) {
            search = search.trim().toLowerCase();
            st.setString(1, "%" + search + "%");
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                String actors = resultSet.getString("actors");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String rating = resultSet.getString("rating");

                movies.add(new Movie(title,year,actors,director,genre, rating));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findMovieInDatabaseByYear(String search) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = database.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SELECT_MOVIE_BY_YEAR_SQL)) {
            st.setString(1, search);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                String actors = resultSet.getString("actors");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String rating = resultSet.getString("rating");

                movies.add(new Movie(title,year,actors,director,genre, rating));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findMovieInDatabaseByDirector(String search) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = database.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SELECT_MOVIE_BY_DIRECTOR_SQL)) {
            st.setString(1, search);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                String actors = resultSet.getString("actors");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String rating = resultSet.getString("rating");

                movies.add(new Movie(title,year,actors,director,genre, rating));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> findMovieInDatabaseByRating(String search) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = database.getConnection();
        try (PreparedStatement st = connection.prepareStatement(SELECT_MOVIE_BY_RATING)) {
            st.setString(1, search);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                String actors = resultSet.getString("actors");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String rating = resultSet.getString("rating");

                movies.add(new Movie(title,year,actors,director,genre, rating));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Movie> findInDatabase(String sqlQuery, String search) {
        List<Movie> movies = new ArrayList<>();

        Connection connection = database.getConnection();
        try (PreparedStatement st = connection.prepareStatement(sqlQuery)) {
            st.setString(1, search);
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String year = resultSet.getString("year");
                String actors = resultSet.getString("actors");
                String director = resultSet.getString("director");
                String genre = resultSet.getString("genre");
                String rating = resultSet.getString("rating");

                movies.add(new Movie(title,year,actors,director,genre, rating));
            }
            return movies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
