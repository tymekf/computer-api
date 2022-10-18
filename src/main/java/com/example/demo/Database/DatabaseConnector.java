package com.example.demo.Database;

import com.example.demo.ComputerCreator;
import com.example.demo.Models.Computer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {

    static final String DB_URL = "jdbc:mysql://localhost:3306/computers";
    static final String DB_USERNAME = "root";
    static final String DB_PASSWORD = "admin1234";

    public void generate() {
        ComputerCreator computerCreator = new ComputerCreator();
        List<Computer> computers = new ArrayList<>(computerCreator.createComputers());
        insertDataIntoDatabase(computers);
    }

    public void insertDataIntoDatabase(List<Computer> computerList) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            for (Computer c : computerList) {
                String sql = "INSERT into faktura "
                        + " (" + Computer.name + ", " + Computer.date + ", " + Computer.usdPrice + ", " + Computer.plnPrice +")"
                        + "values ('" + c.getComputerName() + "', '" + c.getPostingDate() + "', '" + c.getPriceInUsd() + "', '" + c.getPriceInPln() + "')";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}