package com.example.bahanur.data;


import com.example.bahanur.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoriesIDAO {

    List<Category> getCategories();
    Category addCategory(Category category);
    void removeCategory(Category category);
}
