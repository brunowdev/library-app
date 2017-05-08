package com.library.app.category.services.impl;

import com.library.app.category.exceptions.CategoryExistentException;
import com.library.app.category.exceptions.CategoryNotFoundException;
import com.library.app.category.model.Category;
import com.library.app.category.repository.CategoryRepository;
import com.library.app.category.services.CategoryService;
import com.library.app.common.exceptions.FieldNotValidException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    Validator validator;
    CategoryRepository categoryRepository;

    @Override
    public Category add(Category category) throws FieldNotValidException {

        final Set<ConstraintViolation<Category>> errors = validator.validate(category);

        final Iterator<ConstraintViolation<Category>> itErros = errors.iterator();

        if (itErros.hasNext()) {
            final ConstraintViolation<Category> violation = itErros.next();
            throw new FieldNotValidException(violation.getPropertyPath().toString(), violation.getMessage());
        }

        if (categoryRepository.alreadyExists(category)) {
            throw new CategoryExistentException();
        }

        return categoryRepository.add(category);
    }

    @Override
    public void update(Category category) throws FieldNotValidException {

        final Set<ConstraintViolation<Category>> errors = validator.validate(category);

        final Iterator<ConstraintViolation<Category>> errorsIterator = errors.iterator();

        if (errorsIterator.hasNext()) {
            final ConstraintViolation<Category> violation = errorsIterator.next();
            throw new FieldNotValidException(violation.getPropertyPath().toString(), violation.getMessage());
        }

        if (categoryRepository.alreadyExists(category)) {
            throw new CategoryExistentException();
        }

        if (!categoryRepository.existsById(category.getId())) {
            throw new CategoryNotFoundException();
        }

        categoryRepository.update(category);
    }

    @Override
    public Category findById(Long id) {

        final Category category = categoryRepository.findById(id);

        if (category == null) {
            throw new CategoryNotFoundException();
        }

        return category;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
