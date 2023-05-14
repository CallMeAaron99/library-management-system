package com.library.service;

import com.library.mapper.BookMapper;
import com.library.mapper.CategoryMapper;
import com.library.pojo.Book;
import com.library.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryMapper categoryMapper;

    private final BookMapper bookMapper;

    @Autowired
    public CategoryService(CategoryMapper categoryMapper, BookMapper bookMapper) {
        this.categoryMapper = categoryMapper;
        this.bookMapper = bookMapper;
    }

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    public Category findById(int id) {
        return categoryMapper.findById(id);
    }

    public void insert(Category category) {
        categoryMapper.insert(category);
    }

    public void update(Category category) {
        categoryMapper.update(category);
    }

    public void delete(int id) {
        categoryMapper.delete(id);
    }

    public void insertOrDelete(List<String> categoryTags) {
        // 找出所有类型
        List<Category> allCategories = categoryMapper.findAll();

        // 根据所有类型创建一个 map<类型名字, 类型id>
        Map<String, Integer> nameToIdMap = allCategories.stream()
                .collect(Collectors.toMap(Category::getName, Category::getId));

        // 删除所有名字不在 categoryTags
        for (Map.Entry<String, Integer> entry : nameToIdMap.entrySet()) {
            String name = entry.getKey();
            Integer id = entry.getValue();
            if (!categoryTags.contains(name)) {
                // 查找类型相关联的图书
                List<Book> books = bookMapper.findByCategoryId(id);
                if (books.isEmpty()) {
                    // 没有相关联图书，可以删除类型
                    categoryMapper.delete(id);
                }
            }
        }

        // 创建新的类型
        for (String tag : categoryTags) {
            if (!nameToIdMap.containsKey(tag)) {
                Category category = new Category();
                category.setName(tag);
                categoryMapper.insert(category);
            }
        }
    }
}