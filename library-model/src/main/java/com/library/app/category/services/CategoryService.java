package com.library.app.category.services;

import java.util.List;

import com.library.app.category.exceptions.CategoryExistentException;
import com.library.app.category.model.Category;
import com.library.app.common.exceptions.FieldNotValidException;

public interface CategoryService {

	Category add(Category category) throws FieldNotValidException, CategoryExistentException;

	void update(Category category) throws FieldNotValidException;

	List<Category> findAll();

}
