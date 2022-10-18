package com.example.demo.Database;

import com.example.demo.Models.Computer;
import java.sql.*;
import static com.example.demo.Database.DatabaseConnector.*;

public class DatabaseController {

    DatabasePrinter databasePrinter = new DatabasePrinter();

    public void returnDatabaseSortedByColumnNumber(int columnNumber) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            String selectAll = "SELECT * FROM faktura ORDER BY " + (columnNumber + 1);
            ResultSet resultSet = statement.executeQuery(selectAll);
            System.out.println("sortowanie po kolumnie " + columnNumber);
            databasePrinter.printHeader();
            while (resultSet.next()) {
                databasePrinter.printRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void searchDatabaseForDate(String date) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            databasePrinter.printHeader();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM faktura");
            while (resultSet.next()) {
                if (resultSet.getString(Computer.date).contains(date)) {
                    databasePrinter.printRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchDatabaseForComputerName(String computerName) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            databasePrinter.printHeader();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM faktura");
            while (resultSet.next()) {
                if (resultSet.getString(Computer.name).contains(computerName)) {
                    databasePrinter.printRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}