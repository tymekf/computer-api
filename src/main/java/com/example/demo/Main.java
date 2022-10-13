package com.example.demo;

public class Main {

    public void start() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.generate();
        Console console = new Console();
        console.printInitialMenu();
    }
}