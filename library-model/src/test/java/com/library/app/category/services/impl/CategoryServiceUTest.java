package com.library.app.category.services.impl;

import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Before;
import org.junit.Test;

import com.library.app.category.exceptions.CategoryExistentException;
import com.library.app.category.exceptions.CategoryNotFoundException;
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
		when(categoryRepository.add(category)).thenReturn(randomCategory(category, 10L));

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

	@Test
	public void updateWithNullName() {
		updateCategoryWithInvalidName(null);
	}

	@Test
	public void updateCategoryWithShortName() {
		updateCategoryWithInvalidName("A");
	}

	@Test
	public void updateCategoryWithLongName() {
		updateCategoryWithInvalidName(
				"This is a long name that will cause an exception to be thrown This is a long name that will cause an exception to be thrown This is a long name that will cause an exception to be thrown");
	}

	@Test(expected = CategoryExistentException.class)
	public void updateCategoryWithExistentName() {

		final Category randomCategory = createRandomCategory();

		when(categoryRepository.alreadyExists(randomCategory(randomCategory, 1L))).thenReturn(true);

		categoryService.update(randomCategory(randomCategory, 1L));
	}

	@Test(expected = CategoryNotFoundException.class)
	public void updateCategoryNotFound() {

		final Category randomCategory = createRandomCategory();

		when(categoryRepository.alreadyExists(randomCategory(randomCategory, 1L))).thenReturn(false);
		when(categoryRepository.existsById(1L)).thenReturn(false);

		categoryService.update(randomCategory(randomCategory, 1L));
	}

	@Test
	public void updateValidCategory() {

		final Category randomCategory = createRandomCategory();

		when(categoryRepository.alreadyExists(randomCategory(randomCategory, 1L))).thenReturn(false);
		when(categoryRepository.existsById(1L)).thenReturn(true);

		categoryService.update(randomCategory(randomCategory, 1L));

		verify(categoryRepository).update(randomCategory(randomCategory, 1L));
	}

	@Test
	public void findCategoryById() {

		final Category randomCategory = createRandomCategory();

		when(categoryRepository.findById(1L)).thenReturn(randomCategory);

		Category find = categoryRepository.findById(1L);

		assertThat(find.getId(), is(equalTo(randomCategory.getId())));
		assertThat(find.getName(), is(equalTo(randomCategory.getName())));

	}

	@Test(expected = CategoryNotFoundException.class)
	public void findCategoryByIdNotFound() {

		when(categoryRepository.findById(1L)).thenReturn(null);

		categoryService.findById(1L);
	}

	@Test
	public void findAllNoCategories() {
		when(categoryRepository.findAll()).thenReturn(new ArrayList<>(0));

		final List<Category> categories = categoryService.findAll();
		assertThat(categories.isEmpty(), is(equalTo(true)));
	}

	private void updateCategoryWithInvalidName(final String name) {
		try {
			categoryService.update(new Category(name));
			fail("An error should have been thrown");
		} catch (final FieldNotValidException e) {
			assertThat(e.getFieldName(), is(equalTo("name")));
		}
	}

}
