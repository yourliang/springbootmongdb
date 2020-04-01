package com.example.springbootmongdb.service;

import com.example.springbootmongdb.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MongoDbServiceImp implements MongoDbService {

    private static final Logger logger = LoggerFactory.getLogger(MongoDbService.class);

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public String saveObj(Book book) {
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        mongoTemplate.save(book);
        return "添加成功";
    }

    @Override
    public List<Book> findAll() {
        return mongoTemplate.findAll(Book.class);
    }

    @Override
    public Book getBookById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Book.class);
    }

    @Override
    public Book getBookByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Book.class);
    }

    @Override
    public String updateBook(Book book) {
        Query query = new Query(Criteria.where("_id").is(book.getId()));

        Update update = new Update().set("publish", book.getPublish())
                .set("info", book.getInfo())
                .set("updateTime",  new Date());

        mongoTemplate.updateFirst(query, update, Book.class);
        return null;
    }

    @Override
    public String deleteBook(Book book) {
        mongoTemplate.remove(book);
        return "success";
    }

    @Override
    public String deleteBookById(String id) {
        Book book = getBookById(id);
        deleteBook(book);
        return "success";
    }
}
