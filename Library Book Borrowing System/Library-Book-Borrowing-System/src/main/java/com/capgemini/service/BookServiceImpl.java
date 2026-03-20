package com.capgemini.service;


import com.capgemini.entity.Book;
import com.capgemini.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repo;

    @Override
    public Map<String, Object> addBook(Book book) {

        if (book.getAvailableCopies() < 0) {
            return Map.of("message", "Available copies cannot be negative");
        }

        book.setBorrowedCopies(0);

        Book saved = repo.save(book);

        return Map.of(
                "message", "Book added successfully",
                "bookId", saved.getId()
        );
    }

    @Override
    public Book getBookById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    @Override
    public Map<String, String> updateBook(int id, Book newBook) {

        Book existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (newBook.getAvailableCopies() < 0) {
            return Map.of("message", "Available copies cannot be negative");
        }

        existing.setTitle(newBook.getTitle());
        existing.setAuthor(newBook.getAuthor());
        existing.setAvailableCopies(newBook.getAvailableCopies());

        repo.save(existing);

        return Map.of("message", "Book updated successfully");
    }

    @Override
    public Map<String, String> deleteBook(int id) {

        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        repo.delete(book);

        return Map.of("message", "Book deleted successfully");
    }

    @Override
    public Map<String, String> borrowBook(int id) {

        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() == 0) {
            return Map.of("message", "No copies available");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        book.setBorrowedCopies(book.getBorrowedCopies() + 1);

        repo.save(book);

        return Map.of("message", "Book borrowed successfully");
    }

    @Override
    public Map<String, String> returnBook(int id) {

        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getBorrowedCopies() == 0) {
            return Map.of("message", "No borrowed books to return");
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        book.setBorrowedCopies(book.getBorrowedCopies() - 1);

        repo.save(book);

        return Map.of("message", "Book returned successfully");
    }
}