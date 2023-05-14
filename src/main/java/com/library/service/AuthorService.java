package com.library.service;

import com.library.mapper.AuthorMapper;
import com.library.pojo.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public List<Author> findAll() {
        return authorMapper.findAll();
    }

    public Author findById(int id) {
        return authorMapper.findById(id);
    }

    public Author findByName(String name){
        return authorMapper.findByName(name);
    }

    public void insert(Author author) {
        authorMapper.insert(author);
    }

    public void update(Author author) {
        authorMapper.update(author);
    }

    public void delete(int id) {
        authorMapper.delete(id);
    }
}
