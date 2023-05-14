package com.library.controller;

import com.library.pojo.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int categoryId,
            @RequestParam(defaultValue = "0") int authorId) {
        List<Book> books = bookService.findAll(page, pageSize, title, categoryId, authorId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/count")
    public int count(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") int categoryId,
            @RequestParam(defaultValue = "0") int authorId) {
        return bookService.count(title, categoryId, authorId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable int id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Book book) {
        // 数据验证
        if(Objects.equals(book.getTitle(), "") || book.getCategory().getId() == 0 || book.getAuthorTags().isEmpty())
            return ResponseEntity.badRequest().build();
        bookService.insert(book);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable int id, @RequestBody Book book) {
        // 数据验证
        if(Objects.equals(book.getTitle(), "") || book.getCategory().getId() == 0 || book.getAuthorTags().isEmpty())
            return ResponseEntity.badRequest().build();
        book.setId(id);
        bookService.update(book);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}