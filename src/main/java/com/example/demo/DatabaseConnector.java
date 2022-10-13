package com.example.demo;

import com.example.demo.Models.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    private static String url = "jdbc:mysql://localhost:3306/computers";
    private static String username = "root";
    private static String password = "admin1234";

    public void printHeader() {
        System.out.println("------------------------------------------------------------\n"
                + "|    nazwa     | data_ksiegowania | koszt_USD |  koszt_PLN | \n"
                + "------------------------------------------------------------");
    }

    public void printRow(ResultSet resultSet) throws SQLException {
        System.out.println("|  " + resultSet.getString("nazwa") + "  |    "
                + resultSet.getString("data_ksiegowania") + "    |   "
                + resultSet.getString("koszt_USD") + "   |   "
                + resultSet.getString("koszt_PLN") + "  | "
                + "\n------------------------------------------------------------");
    }


    public void generate() {
        ComputerCreator computerCreator = new ComputerCreator();
        List<Computer> computers = new ArrayList<>(computerCreator.createComputers());
        insertDataIntoDatabase(computers);
    }

    public void insertDataIntoDatabase(List<Computer> computerList) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            for (Computer c : computerList) {
                String sql = "INSERT into faktura "
                        + " (nazwa, data_ksiegowania, koszt_USD, koszt_PLN)"
                        + "values ('" + c.getName() + "', '" + c.getPostingDate() + "', '" + c.getPriceInUsd() + "', '" + c.getPriceInPln() + "')";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printDatabaseSortedByColumnNumber(int columnNumber) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String selectAll = "SELECT * FROM faktura ORDER BY " + (columnNumber + 1);
            ResultSet resultSet = statement.executeQuery(selectAll);
            printHeader();
            while (resultSet.next()) {
                printRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void searchDatabaseForDate(String date) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            printHeader();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM faktura");
            while (resultSet.next()) {
                if (resultSet.getString("data_ksiegowania").contains(date)) {
                    printRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchDatabaseForComputerName(String computerName) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            System.out.println(computerName);
            System.out.println("------------------------------------------------------------\n"
                    + "|    nazwa     | data_ksiegowania | koszt_USD |  koszt_PLN | \n"
                    + "------------------------------------------------------------");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM faktura");
            while (resultSet.next()) {
                if (resultSet.getString("nazwa").contains(computerName)) {
                    printRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}