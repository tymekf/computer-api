package com.example.demo;

import java.util.Scanner;

public class Console {

    Scanner scanner = new Scanner(System.in);
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public  void printInitialMenu() {
        System.out.println("\nWciśnij odpowiedni klawisz numeryczny:\n" +
                "1. Wyświetl dane z bazy posortowane według odpowiedniej kolumny\n" +
                "2. Wyszukaj w bazie danych po nazwie\n" +
                "3. Wyszukaj w bazie danych po dacie\n" +
                "4. Wyświetl plik XML");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                sortTable();
            case 2:
                searchForAComputer();
            case 3:
                tinder();
            case 4:
                XmlModifier xmlModifier = new XmlModifier();
                xmlModifier.generateXml();
                backToInitialMenu();
            default:
                userDidSomethingWrong();
        }
    }

    private void userDidSomethingWrong() {
        System.out.println("Proszę przeczytać dokładnie instrukcję");
        printInitialMenu();
    }

    private void tinder() {
        scanner.nextLine();
        System.out.println("Wpisz datę:");
        String date = scanner.nextLine();
        databaseConnector.searchDatabaseForDate(date);
        backToInitialMenu();
    }

    private void searchForAComputer() {
        scanner.nextLine();
        System.out.println("Wpisz nazwę szukanego przez siebie komputera: ");
        String computerName = scanner.nextLine();
        databaseConnector.searchDatabaseForComputerName(computerName);
        backToInitialMenu();
    }

    private void sortTable() {
        System.out.println("Po której kolumnie chcesz posortować?\n" +
                "1. nazwa\n" +
                "2. data_ksiegowania\n" +
                "3. koszt_USD\n" +
                "4. koszt_PLN");
        int columnNumber = scanner.nextInt();
        if (columnNumber < 1 || columnNumber > 4) {
            userDidSomethingWrong();
        } else {
            databaseConnector.printDatabaseSortedByColumnNumber(columnNumber);
        }
        backToInitialMenu();
    }

    private void backToInitialMenu() {
        System.out.println("\nWciśnij dowolny klawisz alfanumeryczny, żeby powrócić do poprzedniego menu");
        scanner.next();
        printInitialMenu();
    }
}