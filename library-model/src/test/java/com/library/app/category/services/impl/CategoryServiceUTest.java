package com.library.app.category.services.impl;

import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

import com.library.app.category.exceptions.CategoryExistentException;
import com.library.app.category.model.Category;
import com.library.app.category.repository.CategoryRepository;
import com.library.app.category.services.CategoryService;
import com.library.app.common.exceptions.FieldNotValidException;

public class CategoryServiceUTest {

	private CategoryRepository categoryRepository;
	private CategoryService categoryService;
	private Validator validator;

	@Before
	public void init() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();

		categoryRepository = mock(CategoryRepository.class);

		categoryService = new CategoryServiceImpl();
		((CategoryServiceImpl) categoryService).validator = validator;
		((CategoryServiceImpl) categoryService).categoryRepository = categoryRepository;
	}

	@Test
	public void addCategoryWithNullName() {

		addCategoryWithInvalidName(new Category());

	}

	@Test
	public void addCategoryWithShortName() {

		addCategoryWithInvalidName(new Category("A"));

	}

	@Test
	public void addCategoryWithLongName() {

		addCategoryWithInvalidName(new Category(
				"This is a long name that will cause an exception to be thrown This is a long name that will cause an exception to be thrown This is a long name that will cause an exception to be thrown"));

	}

	@Test(expected = CategoryExistentException.class)
	public void addCategoryAlreadyExists() {

		final Category category = createRandomCategory();

		when(categoryRepository.alreadyExists(category)).thenReturn(true);

		categoryService.add(category);
	}

	@Test
	public void addValidCategory() {

		final Category category = createRandomCategory();

		when(categoryRepository.alreadyExists(category)).thenReturn(false);
		when(categoryRepository.add(category)).thenReturn(categoryWithId(category, 10L));

		final Category persisted = categoryService.add(category);
		assertThat(category.getId(), is(equalTo(persisted.getId())));
		assertThat(category.getName(), is(equalTo(persisted.getName())));
	}

	private void addCategoryWithInvalidName(Category category) {

		try {
			categoryService.add(category);
		} catch (final FieldNotValidException fieldNotValidException) {
			assertThat(fieldNotValidException.getFieldName(), is(equalTo("name")));
		}

	}

}
