package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Introduceti una din comenzile view, report ,export sau exit:");
            Repository repository = new Repository("master_directory");
            String command = scanner.nextLine();
            try {
                if (command.equals("exit")) {
                    System.out.println("Aplicatia se închide...");
                    break;
                } else if (command.equals("view")) {

                    System.out.println("Introduceti numele persoanei:");
                    String personName = scanner.nextLine();

                    System.out.println("Introduceti ID-ul persoanei:");
                    String personId = scanner.nextLine();

                    System.out.println("Introduceti titlul documentului:");
                    String documentTitle = scanner.nextLine();

                    View viewCommand = new View(repository);
                    viewCommand.execute(personName, personId, documentTitle);
                }
                else if(command.equals("report"))
                {
                    Report reportCommand = new Report(repository);
                    reportCommand.execute();
                }
                else if(command.equals("export")) {
                    Export exportCommand = new Export(repository);
                    exportCommand.execute();
                }
                else
                    throw new CustomException("Comandă necunoscută: " + command);
            } catch (CustomException e) {
                System.err.println("A aparut o eroare: " + e);
            }
        }
    }
}

