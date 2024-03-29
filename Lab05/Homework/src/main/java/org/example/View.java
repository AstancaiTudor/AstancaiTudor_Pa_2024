package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Class View is responsible for opening a document from the repository.
 * @author Tudor
 */
public class View {
    private Repository repository;

    /**
     * Constructor for the View class.
     * @param repository The repository to be used.
     */
    public View(Repository repository) {
        this.repository = repository;
    }

    /**
     * Opens a document from the repository.
     * If the document is found, it is opened using the default application associated with the file type.
     * If the document is not found, a custom exception is thrown.
     * @param personName The name of the person.
     * @param personId The ID of the person.
     * @param documentTitle The title of the document.
     */
    public void execute(String personName, String personId, String documentTitle) {
        Desktop desktop = Desktop.getDesktop();
        Person person = new Person(personName, personId);
        List<Document> documents = repository.getDocuments().get(person);

        if (documents == null || documents.isEmpty()) {
            System.err.println("No documents found for: " + personName + " " + personId);
            return;
        }

        Optional<Document> document = documents.stream()
                .filter(d -> d.title().equals(documentTitle))
                .findFirst();

        if (!document.isPresent()) {
            System.err.println("File not found in " + personName + " " + personId + " with title " + documentTitle);
            return;
        }

        String filePath = repository.getDirectory() + File.separator + personName + "_" + personId + File.separator + documentTitle + "." + document.get().extension();
        File file = new File(filePath);

        try {
            desktop.open(file);
        } catch (IOException e) {
            System.err.println("Could not open the document: " + documentTitle);
            e.printStackTrace();
        }
    }

}
