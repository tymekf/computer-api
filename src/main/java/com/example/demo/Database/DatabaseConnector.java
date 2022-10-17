package com.example.demo.Database;

import com.example.demo.Models.ComputerCreator;
import com.example.demo.Models.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    static String DB_URL = "jdbc:mysql://localhost:3306/computers";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "admin1234";
    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    Statement statement = connection.createStatement();
    DatabasePrinter databasePrinter = new DatabasePrinter();

    public DatabaseConnector() throws SQLException {
    }

    public void generateDataToBePutInDatabase() {
        ComputerCreator computerCreator = new ComputerCreator();
        List<Computer> computers = new ArrayList<>(computerCreator.createComputers());
        establishDatabase(computers);
    }

    void establishDatabase(List<Computer> computerList) {
        try {
//            new DatabaseConnector();
            for (Computer c : computerList) {
                insertDataIntoDatabase(c, statement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDataIntoDatabase(Computer c, Statement statement) throws SQLException {
        String sqlInsert = "INSERT into faktura "
                + " (nazwa, data_ksiegowania, koszt_USD, koszt_PLN)"
                + "values ('" + c.getName() + "', '" + c.getPostingDate() + "', '" + c.getPriceInUsd() + "', '" + c.getPriceInPln() + "')";
        statement.executeUpdate(sqlInsert);
    }

//    public void returnDatabaseSortedByColumnNumber(int columnNumber) {
//        try {
////            new DatabaseConnector();
//            String selectAll = "SELECT * FROM faktura ORDER BY " + (columnNumber + 1);
//            ResultSet resultSet = statement.executeQuery(selectAll);
//           databasePrinter.printHeader();
//            while (resultSet.next()) {
//                databasePrinter.printRow(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}