package com.library.app.category.repository;

import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.library.app.category.model.Category;
import com.library.app.commontests.BaseUTest;
import com.library.app.commontests.util.DBCommand;

public class CategoryRepositoryUTest extends BaseUTest {

	private static CategoryRepository categoryRepository;

	@Before
	public void initializeObjects() {
		categoryRepository = new CategoryRepository();
		categoryRepository.injectEntityManager(em);
	}

	@Test
	public void addCategoryAndFindIt() {

		final Long categoryAddedId = dbCommandTransactionalExecutor.executeCommand(new DBCommand<Long>() {

			@Override
			public Long execute() {
				return categoryRepository.add(createSimpleCategory()).getId();
			}

		});

		final Category categoryFindded = categoryRepository.findById(categoryAddedId);
		assertThat(categoryFindded, is(notNullValue()));
		assertThat(categoryFindded.getName(), is(equalTo(createSimpleCategory().getName())));

	}

}
