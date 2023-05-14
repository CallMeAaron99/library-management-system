package com.library.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    private int id;
    private String title;
    private Category category;
    private List<Author> authors;
    private List<String> authorTags;
}