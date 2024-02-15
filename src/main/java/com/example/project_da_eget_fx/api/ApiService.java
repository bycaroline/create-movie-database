/**
 * Class for fetching data from the API
 */

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

    /**
     * Method for fetching data from the API based on movie title
     */
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

    /**
     * Method for formatting URL to API standard
     */
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

    /**
     * Method for formatting movie title to API standard
     */
    private static String formatToApiStandard(String movieTitle) {
        String formattedTitle = movieTitle.replace(" ", "+");
        return formattedTitle;
    }
}


