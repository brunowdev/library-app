package com.library.app.category.repository;

import com.library.app.category.model.Category;
import com.library.app.common.BaseRepository;

public class CategoryRepository extends BaseRepository<Category, Long> {

    public boolean alreadyExists(final Category category) {
        return alreadyExists("name", category.getName(), category.getId());
    }

}
