package com.example.project_da_eget_fx;

import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    static Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("API_KEY");
    private static final String DB_USERNAME = dotenv.get("DB_USERNAME");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");
    //private static final String DB_DRIVER = "";
    //private static final String DB_SERVER = "";
    private static final String DB_PORT = dotenv.get("DB_PORT");
    private static final String DB_DATABASE = dotenv.get("DB_DATABASE");

    private static final String DB_URL = dotenv.get("DB_URL");

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getDbUsername() {
        return DB_USERNAME;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static String getDbPort() {
        return DB_PORT;
    }

    public static String getDbDatabase() {
        return DB_DATABASE;
    }

    public static String getDbURL() {
        return DB_URL;
    }
}

