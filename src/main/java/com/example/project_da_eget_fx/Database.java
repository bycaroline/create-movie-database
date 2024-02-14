package com.example.project_da_eget_fx;

import java.sql.*;

public class Database {
    // Skapa URL till databasen
    private static final String DB_DATABASE = AppConfig.getDbDatabase();
    //    private static final String DB_DRIVER = AppConfig.getDbDriver();
//    private static final String DB_SERVER = AppConfig.getDbServer();
    private static final String DB_PORT = AppConfig.getDbPort();

    private static final String DB_URL = AppConfig.getDbURL();

    private static final String JDBC_URL = "jdbc:" + DB_URL;

    private final String jdbcUsername = AppConfig.getDbUsername();
    private final String jdbcPassword = AppConfig.getDbPassword();

    // SQL query for creating the database
    private static final String CREATE_DATABASE_SQL = "CREATE DATABASE IF NOT EXISTS " + DB_DATABASE;

    public Database() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, jdbcUsername, jdbcPassword)) {
            createDatabase(connection);
            connection.setCatalog(DB_DATABASE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDatabase(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DATABASE_SQL)) {
            preparedStatement.executeUpdate();
            System.out.println("Database '" + DB_DATABASE + "' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printSQLException(SQLException ex) {
        while (ex != null) {
            System.err.println("SQL State: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("Message: " + ex.getMessage());
            ex = ex.getNextException();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

