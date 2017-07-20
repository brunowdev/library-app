package com.library.app.category.resource;

import com.library.app.category.model.Category;
import com.library.app.category.services.CategoryService;
import com.library.app.category.utils.JsonTestUtils;
import com.library.app.category.utils.TestFileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static com.library.app.category.utils.JsonTestUtils.assertJsonMatchesExpectedJson;
import static com.library.app.category.utils.JsonTestUtils.readJsonFile;
import static com.library.app.category.utils.TestFileUtils.getPathFileRequest;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by BRUNO-PC on 07/05/2017.
 */
public class CategoryResourceUTest {

    private CategoryResource resource;

    private static final String PATH = "categories";

    @Mock
    private CategoryService service;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        resource = new CategoryResource();

        resource.service = this.service;
    }

    @Test
    public void addValidCategory() {

        when(service.add(new Category("Java EE"))).thenReturn(new Category(1L, "Java EE"));

        final Response res = resource.add(readJsonFile(getPathFileRequest(PATH, "newCategory.json")));

        assertThat(res.getStatus(), is(equalTo(CREATED)));
        assertJsonMatchesExpectedJson(res.getEntity().toString(), "{\"id\":1}");

    }

}
