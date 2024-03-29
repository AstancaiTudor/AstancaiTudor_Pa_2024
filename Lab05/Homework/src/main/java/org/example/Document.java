package org.example;

/**
 * Represents a document.
 * @param author The author of the document.
 * @param title The title of the document.
 * @param extension The extension of the document.
 */
public record Document(Person author, String title, String extension) {}
