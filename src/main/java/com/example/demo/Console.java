package com.example.demo;

import com.example.demo.Database.DatabaseConnector;
import com.example.demo.Database.DatabaseController;
import com.example.demo.Xml.XmlModifier;

import java.sql.SQLException;
import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);
    DatabaseConnector databaseConnector = new DatabaseConnector();
    DatabaseController databaseController = new DatabaseController();

    private static final String initialMenu = "\nWciśnij odpowiedni klawisz numeryczny:\n" +
            "1. Wyświetl dane z bazy posortowane według odpowiedniej kolumny\n" +
            "2. Wyszukaj w bazie danych po nazwie\n" +
            "3. Wyszukaj w bazie danych po dacie\n" +
            "4. Wyświetl plik XML\n" +
            "5. Zamknij program\n";

    public Console() throws SQLException {
    }

    void printInitialMenu() {
        System.out.println(initialMenu);
        initialMenuChoice();
    }

    private void unfortunatelyUserDidSomethingWrong() {
        System.out.println("Proszę przeczytać dokładnie instrukcję");
        emptyLine();
        printInitialMenu();
    }

    private void emptyLine() {
        scanner.nextLine();
    }

    private void tinder() {
        emptyLine();
        System.out.println("Wpisz datę:");
        String date = scanner.nextLine();
        databaseController.searchDatabaseForDate(date);
        printInitialMenu();
    }

    private void searchForAComputer() {
        emptyLine();
        System.out.println("Wpisz nazwę szukanego przez siebie komputera: ");
        String computerName = scanner.nextLine();
        databaseController.searchDatabaseForComputerName(computerName);
        printInitialMenu();
    }

    private void sortTable() {
        System.out.println("Po której kolumnie chcesz posortować?\n" +
                "1. nazwa\n" +
                "2. data_ksiegowania\n" +
                "3. koszt_USD\n" +
                "4. koszt_PLN");
        String columnNumber = scanner.next();

        switch (columnNumber) {
            case "1":
            case "2":
            case "3":
            case "4":
                databaseController.returnDatabaseSortedByColumnNumber(Integer.parseInt(columnNumber));
                break;
            default:
                unfortunatelyUserDidSomethingWrong();
                break;
        }
        printInitialMenu();
    }

    void initialMenuChoice() {
        String choice = scanner.next();
        switch (choice) {
            case "1":
                sortTable();
                break;
            case "2":
                searchForAComputer();
                break;
            case "3":
                tinder();
                break;
            case "4":
                XmlModifier xmlModifier = new XmlModifier();
                xmlModifier.generateXml();
                printInitialMenu();
                break;
            case "5":
                System.exit(0);
            default:
                unfortunatelyUserDidSomethingWrong();
                break;
        }
    }
}