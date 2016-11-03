package com.library.app.category.repository;

import static com.library.app.commontests.category.CategoryForTestsRepository.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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
	}

	@Test
	public void addCategoryAndFindIt() {

		Long categoryAddedId = null;

		try {
			em.getTransaction().begin();
			categoryAddedId = categoryRepository.add(createSimpleCategory()).getId();
			assertThat(categoryAddedId, is(notNullValue()));
			em.getTransaction().commit();
			em.clear();
		} catch (final Exception e) {
			fail("This exception should not have been thrown");
			e.printStackTrace();
			em.getTransaction().rollback();
		}

		final Category categoryFindded = categoryRepository.findById(categoryAddedId);
		assertThat(categoryFindded, is(notNullValue()));
		assertThat(categoryFindded.getName(), is(equalTo(createSimpleCategory().getName())));

	}

}
