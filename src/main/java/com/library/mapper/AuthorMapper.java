package com.library.mapper;

import com.library.pojo.Author;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AuthorMapper {
    @Select("SELECT * FROM author")
    List<Author> findAll();

    @Select("SELECT * FROM author WHERE id = #{id}")
    Author findById(@Param("id") int id);

    @Select("SELECT * FROM author WHERE name = #{name}")
    Author findByName(@Param("name") String name);

    @Insert("INSERT INTO author (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Author author);

    @Update("UPDATE author SET name = #{name} WHERE id = #{id}")
    void update(Author author);

    @Delete("DELETE FROM author WHERE id = #{id}")
    void delete(int id);
}