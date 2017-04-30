package com.library.app.category.repository;

import com.library.app.category.model.Category;
import com.library.app.commontests.BaseUTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.library.app.commontests.category.CategoryForTestsRepository.createRandomCategory;
import static com.library.app.commontests.category.CategoryForTestsRepository.createSimpleCategory;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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

        final Long categoryAddedId = dbCommandTransactionalExecutor.executeCommand(() -> categoryRepository.add(createSimpleCategory()).getId());

        final Category localizedCategory = categoryRepository.findById(categoryAddedId);
        assertThat(localizedCategory, is(notNullValue()));
        assertThat(localizedCategory.getName(), is(equalTo(createSimpleCategory().getName())));

    }

    @Test
    public void notFoundWhenFindCategoryById() {
        final Category localizedCategory = categoryRepository.findById(112358L);
        assertThat(localizedCategory, is(nullValue()));
    }

    @Test
    public void notFoundWhenFindCategoryByNullId() {
        final Category localizedCategory = categoryRepository.findById(null);
        assertThat(localizedCategory, is(nullValue()));
    }

    @Test
    public void updateCategory() {

        // Persist
        final Long categoryAddedId = dbCommandTransactionalExecutor.executeCommand(() -> categoryRepository.add(createRandomCategory()).getId());

        final Category categoryAfterAdd = categoryRepository.findById(categoryAddedId);

        // Update persisted category
        categoryAfterAdd.setName("Update category");
        dbCommandTransactionalExecutor.executeCommand(() -> {
            categoryRepository.update(categoryAfterAdd);
            return null;
        });

        // Validate after update
        final Category localizedCategory = categoryRepository.findById(categoryAddedId);
        assertThat(localizedCategory, is(notNullValue()));
        assertThat(localizedCategory.getName(), is(equalTo("Update category")));

    }

    @Test
    public void findAllCategories() {

        dbCommandTransactionalExecutor.executeCommand(() -> categoryRepository.add(createRandomCategory()).getId());

        final List<Category> localizedCategory = categoryRepository.findAll();

        assertThat(localizedCategory, is(notNullValue()));
        assertThat(localizedCategory.size(), equalTo(1));

    }

    @Test
    public void addCategoryAlreadyExists() {

        dbCommandTransactionalExecutor.executeCommand(() ->
                categoryRepository.add(createSimpleCategory()).getId());

        assertThat(categoryRepository.alreadyExists(createSimpleCategory()), is(equalTo(true)));
        assertThat(categoryRepository.alreadyExists(createRandomCategory()), is(equalTo(false)));

    }

    @Test
    public void updateCategoryNotExistsById() {

        final Long categoryAddedId = dbCommandTransactionalExecutor.executeCommand(() -> categoryRepository.add(createRandomCategory()).getId());

        assertThat(categoryRepository.existsById(10L), is(equalTo(false)));
        assertThat(categoryRepository.existsById(categoryAddedId), is(equalTo(true)));

    }

}
