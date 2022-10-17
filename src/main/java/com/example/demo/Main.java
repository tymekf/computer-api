package com.example.demo;

import com.example.demo.Database.DatabaseConnector;

import java.sql.SQLException;

public class Main {

    public void start() throws SQLException {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.generateDataToBePutInDatabase();
        Console console = new Console();
        console.printInitialMenu();
    }
}