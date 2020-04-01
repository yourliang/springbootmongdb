package com.example.springbootmongdb.service;

import com.example.springbootmongdb.model.Book;

import java.util.List;

public interface MongoDbService {
    String saveObj(Book book);

    List<Book> findAll();

    Book getBookById(String id);

    Book getBookByName(String name);

    String updateBook(Book book);

    String deleteBook(Book book);

    String deleteBookById(String id);
}
