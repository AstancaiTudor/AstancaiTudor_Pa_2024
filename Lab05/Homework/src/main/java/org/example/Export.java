package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Export the repository to a JSON file.
 * @author Tudor
 */
public class Export implements Command
{
    private Repository repository;

    public Export(Repository repository) {
        this.repository = repository;
    }

    /**
     * Export the repository to a JSON file.
     * The documents are serialized using Jackson.
     * The JSON file is saved in the current directory with the name "repository.json".
     */
    public void execute() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(repository.getDocuments());

            try (FileWriter file = new FileWriter("repository.json")) {
                file.write(jsonString);
                System.out.println("Repository exported successfully.");
            } catch (IOException e) {
                System.err.println("Error writing the JSON file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error serializing the documents: " + e.getMessage());
        }
    }
}
