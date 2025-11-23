package org.example.xml.parser.jaxb;

import org.example.xml.parser.jaxb.entity.Book;
import org.example.xml.parser.jaxb.entity.Catalog;

import java.util.List;

public class PrintContextCatalog {

    public static void printCatalog(Catalog catalog, String indent) {
        for (Book book : catalog.getCatalog()) {
            System.out.println("Book: id =\"" + book.getId() + "\"");
            printBook(book, indent);
        }
        System.out.println();
    }

    public static void printBook(Book book, String indent) {
        System.out.println(indent + "Author: " + book.getAuthor());
        System.out.println(indent + "Title: " + book.getTitle());
        System.out.println(indent + "Genre: " + book.getGenre());
        System.out.println(indent + "Price: " + String.format("%.2f", book.getPrice()));
        System.out.println(indent + "Publishing Date: " + book.getPublish_date());
        System.out.println(indent + "Description: " + book.getDescription());
    }
}
