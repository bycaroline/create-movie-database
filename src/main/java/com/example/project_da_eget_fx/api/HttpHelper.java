package com.example.project_da_eget_fx.api;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {
    // Privat constructor för att förhindra instansiering
    JSONObject jsonObject = null;

    private HttpHelper() {
        //this.url = url;
        throw new AssertionError("Instantiating utility class");
    }

    public static String fetchDataFromUrl(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        return response.toString();
    }
}


