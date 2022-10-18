package com.example.demo;

import com.example.demo.Database.DatabaseConnector;
import com.example.demo.Database.DatabaseController;
import com.example.demo.Xml.XmlModifier;
import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);
    DatabaseController databaseController = new DatabaseController();

    public void printInitialMenu() {
        String initialMenu = "\nWciśnij odpowiedni klawisz numeryczny:\n" +
                "1. Wyświetl dane z bazy posortowane według odpowiedniej kolumny\n" +
                "2. Wyszukaj w bazie danych po nazwie\n" +
                "3. Wyszukaj w bazie danych po dacie\n" +
                "4. Wyświetl plik XML\n" +
                "5. Zamknij program\n";
        System.out.println(initialMenu);
        initialMenuChoice();
    }

    private void unfortunatelyUserDidSomethingWrong() {
        System.out.println("Proszę przeczytać dokładnie instrukcję");
        printInitialMenu();
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

    private void emptyLine() {
        scanner.nextLine();
    }

    private void initialMenuChoice() {
        String choice = scanner.next();

        switch (choice) {
            case "1":
                sortTable();
            case "2":
                searchForAComputer();
            case "3":
                tinder();
            case "4":
                XmlModifier xmlModifier = new XmlModifier();
                xmlModifier.generateXml();
                printInitialMenu();
            case "5":
                System.out.println("Do widzenia");
                System.exit(0);
            default:
                unfortunatelyUserDidSomethingWrong();
        }
    }
}
