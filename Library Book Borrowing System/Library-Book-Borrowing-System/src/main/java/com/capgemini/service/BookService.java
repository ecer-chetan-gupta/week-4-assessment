package com.capgemini.service;

import com.capgemini.entity.Book;
import java.util.List;
import java.util.Map;

public interface BookService {

    Map<String, Object> addBook(Book book);

    Book getBookById(int id);

    List<Book> getAllBooks();

    Map<String, String> updateBook(int id, Book book);

    Map<String, String> deleteBook(int id);

    Map<String, String> borrowBook(int id);

    Map<String, String> returnBook(int id);
}