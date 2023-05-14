package com.library.service;

import com.library.mapper.AuthorMapper;
import com.library.mapper.BookMapper;
import com.library.pojo.Author;
import com.library.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Autowired
    public BookService(BookMapper bookMapper, AuthorMapper authorMapper) {
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }

    public List<Book> findAll(int page, int pageSize, String title, int categoryId, int authorId) {
        // 计算 limit 的 offset
        int offset = (page - 1) * pageSize;
        return bookMapper.findAll(offset, pageSize, title, categoryId, authorId);
    }

    public int count(String title, int categoryId, int authorId){
        return bookMapper.count(title, categoryId, authorId);
    }

    public Book findById(int id) {
        return bookMapper.findById(id);
    }

    public void insert(Book book) {
        bookMapper.insert(book);
        InsertBookAuthor(book);
    }

    public void update(Book book) {
        bookMapper.update(book);
        // 删除所有 book_author 表中和 book id 相关数据
        bookMapper.deleteBookAuthorByBookId(book.getId());
        InsertBookAuthor(book);
    }

    private void InsertBookAuthor(Book book) {
        for (String authorName : book.getAuthorTags()) {
            Author author = authorMapper.findByName(authorName);
            if (author == null) {
                // 如果作者不存在创建新作者
                author = new Author();
                author.setName(authorName);
                authorMapper.insert(author);
            }
            bookMapper.insertBookAuthor(book.getId(), author.getId());
        }
    }

    public void delete(int id) {
        bookMapper.delete(id);
        // 删除所有 book_author 表中和 book id 相关数据
        bookMapper.deleteBookAuthorByBookId(id);
    }
}
