package com.example.project_da_eget_fx.api;

import com.example.project_da_eget_fx.AppConfig;
import com.example.project_da_eget_fx.classes.MovieData;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiService {
    private final String API_KEY = AppConfig.getApiKey();

    JSONObject jsonObject = null;

    public MovieData getDataByTitle(String movieTitle) {
        String readyTitle = formatToApiStandard(movieTitle);
        URL readyUrl = urlToFetch(readyTitle);
        JSONObject jsonObject = null;
        String response = null;

        try {
            response = HttpHelper.fetchDataFromUrl(readyUrl);
            jsonObject = new JSONObject(response);

            this.jsonObject = new JSONObject(response.toString()); //skapa en json av strängen

            String title = jsonObject.getString("Title");
            String year = jsonObject.getString("Year");
            String actors = jsonObject.getString("Actors");
            String genre = jsonObject.getString("Genre");
            String director = jsonObject.getString("Director");

            return new MovieData(title, year, actors, genre, director);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public String getString(String title, String year, String actors, String genre, String director) {
//        return jsonObject.getString(title);
//    }

    private URL urlToFetch(String formattedTitle) {
        try {
            String urlString = "http://www.omdbapi.com/?t=" + formattedTitle + "&apikey=" + API_KEY;

            URL newURL = new URL(urlString);

            return newURL;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String formatToApiStandard(String movieTitle) {
        String formattedTitle = movieTitle.replace(" ", "+");
        return formattedTitle;
    }

//    protected URL convertStringToUrlFormat(String urlString) throws Exception {
//        try {
//            return new URL(urlString);
//        } catch (MalformedURLException e) {
//            // If the provided string is not a valid URL, handle the exception
//            throw new MalformedURLException("Invalid URL: " + urlString);
//        }
//    }
}


//import com.example.project_da_eget_fx.AppConfig;
//        import com.example.project_da_eget_fx.classes.Movie;
//        import com.example.project_da_eget_fx.classes.MovieBuilder;
//
//        import org.json.JSONObject;
//
//        import java.io.IOException;
//        import java.net.MalformedURLException;
//        import java.net.URI;
//        import java.net.URL;


//public class ApiService {
//    private final String API_KEY = AppConfig.getApiKey();
//
//    // Metod för att hämta data från API:et baserat på filmtitel
//    public Movie getDataByTitle(String movieTitle) {
//        String readyTitle = formatToApiStandard(movieTitle);
//        URL readyUrl = urlToFetch(readyTitle);
//        JSONObject jsonObject = null;
//        String response = null;
//
//        try {
//            response = HttpHelper.fetchDataFromUrl(readyUrl);
//            jsonObject = new JSONObject(response);
//
//            String title = jsonObject.getString("Title");
//            String year = jsonObject.getString("Year");
//            String actors = jsonObject.getString("Actors");
//            String genre = jsonObject.getString("Genre");
//            String director = jsonObject.getString("Director");
//
//
//            MovieBuilder movieBuilder = new MovieBuilder(title, year, actors, director, genre);
//            Movie newMovie = movieBuilder.build();
//
//            return newMovie;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private URL urlToFetch(String formattedTitle) {
//        try {
//            String urlString = "http://www.omdbapi.com/?t=" + formattedTitle + "&apikey=" + API_KEY;
//
//            URL newURL = new URL(urlString);
//
//            return newURL;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private static String formatToApiStandard(String movieTitle) {
//        String formattedTitle = movieTitle.replace(" ", "+");
//        return formattedTitle;
//    }
//
//    // Hjälpmetod för att skapa URL (underlättar mockning i tester)
//    protected URL convertStringToUrlFormat(String urlString) throws Exception {
//        try {
//            return new URL(urlString);
//        } catch (MalformedURLException e) {
//            // If the provided string is not a valid URL, handle the exception
//            throw new MalformedURLException("Invalid URL: " + urlString);
//        }
//    }
//}
