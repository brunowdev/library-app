package com.library.app.category.repository;

import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.library.app.category.model.Category;
import com.library.app.commontests.BaseUTest;

public class CategoryRepositoryUTest extends BaseUTest {

	private static CategoryRepository categoryRepository;

	@Before
	public void initializeObjects() {
		categoryRepository = new CategoryRepository();
		categoryRepository.injectEntityManager(em);
		categoryRepository.deleteAll();
	}

	@Test
	public void addCategoryAndFindIt() {

		final Long categoryAddedId = dbCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(createSimpleCategory()).getId();
		});

		final Category categoryFindded = categoryRepository.findById(categoryAddedId);
		assertThat(categoryFindded, is(notNullValue()));
		assertThat(categoryFindded.getName(), is(equalTo(createSimpleCategory().getName())));

	}

	@Test
	public void notFoundWhenFindCategoryById() {
		final Category categoryFindded = categoryRepository.findById(112358L);
		assertThat(categoryFindded, is(nullValue()));
	}

	@Test
	public void notFoundWhenFindCategoryByNullId() {
		final Category categoryFindded = categoryRepository.findById(null);
		assertThat(categoryFindded, is(nullValue()));
	}

	@Test
	public void updateCategory() {

		// Persist
		final Long categoryAddedId = dbCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(createRandomCategory()).getId();
		});

		final Category categoryAfterAdd = categoryRepository.findById(categoryAddedId);

		// Update persisted category
		categoryAfterAdd.setName("Update category");
		dbCommandTransactionalExecutor.executeCommand(() -> {
			categoryRepository.update(categoryAfterAdd);
			return null;
		});

		// Validate after update
		final Category categoryFindded = categoryRepository.findById(categoryAddedId);
		assertThat(categoryFindded, is(notNullValue()));
		assertThat(categoryFindded.getName(), is(equalTo("Update category")));

	}

	@Test
	public void findAllCategories() {

		dbCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(createRandomCategory()).getId();
		});

		final List<Category> categoryFindded = categoryRepository.findAll();

		assertThat(categoryFindded, is(notNullValue()));
		assertThat(categoryFindded.size(), equalTo(1));

	}

	@Test
	public void addCategoryAlreadyExists() {

		dbCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(createSimpleCategory()).getId();
		});

		assertThat(categoryRepository.alreadyExists(createSimpleCategory()), is(equalTo(true)));
		assertThat(categoryRepository.alreadyExists(createRandomCategory()), is(equalTo(false)));

	}

	@Test
	public void updateCategoryNotExistsById() {

		final Long categoryAddedId = dbCommandTransactionalExecutor.executeCommand(() -> {
			return categoryRepository.add(createRandomCategory()).getId();
		});

		assertThat(categoryRepository.existsById(10L), is(equalTo(false)));
		assertThat(categoryRepository.existsById(categoryAddedId), is(equalTo(true)));

	}

}
