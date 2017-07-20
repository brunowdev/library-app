package com.library.app.category.resource;

import com.library.app.category.model.Category;
import com.library.app.category.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by BRUNO-PC on 07/05/2017.
 */
public class CategoryResourceUTest {

    private CategoryResource resource;

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

        when(service.add(new Category("Java EE"))).thenReturn(new Category(1l, "Java EE"));

        Response res = resource.add("Java EE");

    }

}
