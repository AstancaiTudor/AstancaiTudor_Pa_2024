package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class View {
    private Repository repository;

    public View(Repository repository) {
        this.repository = repository;
    }

    public void execute(String personName, String personId, String documentTitle) {
        Desktop desktop = Desktop.getDesktop();

        // Găsește persoana și documentul corespunzător în repository
        Person person = new Person(personName, personId);
        List<Document> documents = repository.getDocuments().get(person);

        try {
            if (documents != null) {
                Optional<Document> document = documents.stream()
                        .filter(d -> d.title().equals(documentTitle))
                        .findFirst();
                try {
                    if (document.isPresent()) {
                        String filePath = repository.getDirectory() + File.separator + personName + "_" + personId + File.separator + documentTitle + "." + document.get().extension();
                        File file = new File(filePath);

                        try {
                            if (file.exists()) {
                                try {
                                    desktop.open(file);
                                } catch (IOException e) {
                                    System.out.println("Could not open the document: " + documentTitle);
                                    e.printStackTrace();
                                }
                            } else {
                                throw new CustomException("Document not found: " + filePath);
                            }
                        } catch (CustomException e) {
                            System.err.println(e);
                        }
                    } else {
                        throw new CustomException("File not found " + personName + " " + personId + " with title " + documentTitle);
                    }
                } catch (CustomException e) {
                    System.err.println(e);
                }
            } else {
                throw new CustomException("No documents found for: " + personName + " " + personId);
            }
        } catch (CustomException e) {
            System.err.println(e);
        }

    }
}
