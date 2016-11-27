package com.library.app.category.services.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.library.app.category.exceptions.CategoryExistentException;
import com.library.app.category.exceptions.CategoryNotFoundException;
import com.library.app.category.model.Category;
import com.library.app.category.repository.CategoryRepository;
import com.library.app.category.services.CategoryService;
import com.library.app.common.exceptions.FieldNotValidException;

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

		final Iterator<ConstraintViolation<Category>> itErros = errors.iterator();

		if (itErros.hasNext()) {
			final ConstraintViolation<Category> violation = itErros.next();
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
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

}
