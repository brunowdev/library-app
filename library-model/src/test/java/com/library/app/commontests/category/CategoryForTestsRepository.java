package com.library.app.commontests.category;

import java.util.UUID;

import org.junit.Ignore;

import com.library.app.category.model.Category;

@Ignore
public class CategoryForTestsRepository {

	public static Category createSimpleCategory() {
		return new Category("It Courses");
	}

	public static Category createRandomCategory() {
		return new Category(UUID.randomUUID().toString());
	}

}
