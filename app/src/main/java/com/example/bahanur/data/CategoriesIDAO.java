package com.example.bahanur.data;

import com.example.bahanur.common.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoriesIDAO {
    void open() throws SQLException;
    void close();

    List<Category> getCategories();
    Category addCategory(Category category);
    void removeCategory(Category category);
}
