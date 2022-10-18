package com.example.demo;

import com.example.demo.Database.DatabaseConnector;

public class Main {

    public void start() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.generate();
        Console console = new Console();
        console.printInitialMenu();
    }
}