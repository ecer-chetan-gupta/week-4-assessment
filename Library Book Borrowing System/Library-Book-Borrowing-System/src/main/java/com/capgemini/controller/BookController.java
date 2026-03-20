package com.capgemini.controller;


import com.capgemini.entity.Book;
import com.capgemini.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping
    public Map<String, Object> addBook(@RequestBody Book book) {
        return service.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {
        return service.getBookById(id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @PutMapping("/{id}")
    public Map<String, String> updateBook(@PathVariable int id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteBook(@PathVariable int id) {
        return service.deleteBook(id);
    }

    @PostMapping("/borrow/{id}")
    public Map<String, String> borrowBook(@PathVariable int id) {
        return service.borrowBook(id);
    }

    @PostMapping("/return/{id}")
    public Map<String, String> returnBook(@PathVariable int id) {
        return service.returnBook(id);
    }
}