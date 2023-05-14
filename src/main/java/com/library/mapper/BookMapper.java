package com.library.mapper;

import com.library.pojo.Author;
import com.library.pojo.Book;
import com.library.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectCategory(int id);

    @Select("SELECT author.* FROM author JOIN book_author ON author.id = book_author.author_id WHERE book_author.book_id = #{id}")
    List<Author> selectAuthors(int id);

    // 动态生成 sql
    @Select("<script>" +
            "SELECT b.* FROM book b" +
            "<if test='authorId != 0'>INNER JOIN book_author ba ON b.id = ba.book_id</if>" +
            "<where>" +
            "<if test='title != null and title != \"\"'> AND b.title LIKE CONCAT('%', #{title}, '%')</if>" +
            "<if test='categoryId != 0'> AND b.category_id = #{categoryId}</if>" +
            "<if test='authorId != 0'> AND ba.author_id = #{authorId}</if>" +
            "</where>" +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "category", column = "category_id", one = @One(select = "selectCategory")),
            @Result(property = "authors", column = "id", many = @Many(select = "selectAuthors"))
    })
    List<Book> findAll(int offset, int pageSize, String title, int categoryId, int authorId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM book b" +
            "<if test='authorId != 0'>INNER JOIN book_author ba ON b.id = ba.book_id</if>" +
            "<where>" +
            "<if test='title != null and title != \"\"'> AND b.title LIKE CONCAT('%', #{title}, '%')</if>" +
            "<if test='categoryId != 0'> AND b.category_id = #{categoryId}</if>" +
            "<if test='authorId != 0'> AND ba.author_id = #{authorId}</if>" +
            "</where>" +
            "</script>")
    int count(String title, int categoryId, int authorId);

    @Select("SELECT * FROM book WHERE category_id = #{categoryId}")
    List<Book> findByCategoryId(Integer categoryId);

    @Select("SELECT * FROM book WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "category", column = "category_id", one = @One(select = "selectCategory")),
            @Result(property = "authors", column = "id", many = @Many(select = "selectAuthors"))
    })
    Book findById(int id);

    @Insert("INSERT INTO book (title, category_id) VALUES (#{title}, #{category.id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Book book);

    @Update("UPDATE book SET title = #{title}, category_id = #{category.id} WHERE id = #{id}")
    void update(Book book);

    @Delete("DELETE FROM book WHERE id = #{id}")
    void delete(int id);

    @Delete("DELETE FROM book_author WHERE book_id = #{bookId}")
    void deleteBookAuthorByBookId(int bookId);

    @Insert("INSERT INTO book_author (book_id, author_id) VALUES (#{bookId}, #{authorId})")
    void insertBookAuthor(Integer bookId, Integer authorId);

}