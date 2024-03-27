package org.example;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Repository {
    private String directory;
    private Map<Person, List<Document>> documents = new HashMap<>();

    public Repository(String directory) {
        this.directory = directory;
        loadDocuments();
    }

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
                                System.err.println("A apărut o eroare la parcurgerea directoarelor: " + e.getMessage());
                            }
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Person, List<Document>> getDocuments() {
        return documents;
    }

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

    public String getDirectory() {
        return directory;
    }
}

