package com.example.demo.Database;

import com.example.demo.Models.Computer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePrinter {

    void printHeader() {
        System.out.println("------------------------------------------------------------\n"
                + "|    " + Computer.name + "     | " + Computer.date + " | " + Computer.usdPrice + " |  " + Computer.plnPrice + " | \n"
                + "------------------------------------------------------------");
    }

    void printRow(ResultSet resultSet) throws SQLException {
        System.out.println("|  " + resultSet.getString(Computer.name) + "  |    "
                + resultSet.getString(Computer.date) + "    |   "
                + resultSet.getString(Computer.usdPrice) + "   |   "
                + resultSet.getString(Computer.plnPrice) + "  | "
                + "\n------------------------------------------------------------");
    }
}