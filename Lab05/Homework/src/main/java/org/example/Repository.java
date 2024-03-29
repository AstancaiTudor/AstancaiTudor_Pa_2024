package org.example;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Repository class that loads documents from a directory.
 * @author Tudor
 */
public class Repository {
    private String directory;
    private Map<Person, List<Document>> documents = new HashMap<>();

    /**
     * Constructor that initializes the directory and loads the documents.
     * @param directory The directory to load the documents from.
     */
    public Repository(String directory) {
        this.directory = directory;
        loadDocuments();
    }

    /**
     * Loads the documents from the directory.
     * The documents are stored in a map with the key being the person and the value being a list of documents.
     * The names of the directories are verified to have the format "name_id".
     * There is a custom exception thrown if the directory name is invalid.
     */
    public void loadDocuments() {
        Path start = FileSystems.getDefault().getPath(directory);
        int depth = Integer.MAX_VALUE;

        try (Stream<Path> paths = Files.walk(start, depth)) {
            paths.filter(Files::isDirectory)
                    .forEach(personDirectory -> {
                        if (!personDirectory.equals(start)) {
                            String directoryName = personDirectory.getFileName().toString();
                            String[] parts = directoryName.split("_");

                            try {
                                if (parts.length == 2 && parts[1].matches("\\d+")) {
                                    String personName = parts[0];
                                    String personId = parts[1];
                                    List<Document> personDocuments = null;

                                    try (Stream<Path> filesInDirectory = Files.list(personDirectory)) {
                                        personDocuments = filesInDirectory.map(path -> {
                                            String fileName = path.getFileName().toString();
                                            int lastDotIndex = fileName.lastIndexOf(".");
                                            String fileWithoutExtension = fileName.substring(0, lastDotIndex);
                                            String fileExtension = fileName.substring(lastDotIndex + 1);
                                            return new Document(new Person(personName, personId), fileWithoutExtension, fileExtension);
                                        }).toList();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    documents.put(new Person(personName, personId), personDocuments);
                                } else {
                                    throw new CustomException("Invalid directory name");
                                }
                            } catch (CustomException e) {
                                System.err.println("A aparut o eroare la parcurgerea directoarelor: " + e.getMessage());
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the documents map.
     * @return The documents map.
     */
    public Map<Person, List<Document>> getDocuments() {
        return documents;
    }

    /**
     * Overridden toString method that returns the documents in a readable format.
     * @return The documents in a readable format.
     */
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        for (Person p : documents.keySet()) {
            result.append(p.name()).append(" ").append(p.id()).append(":\n");
            for (Document d : documents.get(p)) {
                result.append(d.title()).append(".").append(d.extension()).append("\n");
            }
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * Getter for the directory.
     * @return The directory.
     */
    public String getDirectory() {
        return directory;
    }
}

