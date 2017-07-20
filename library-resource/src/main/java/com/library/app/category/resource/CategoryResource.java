package com.library.app.category.resource;

import com.library.app.category.model.Category;
import com.library.app.category.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Created by BRUNO-PC on 07/05/2017.
 */
public class CategoryResource {

    CategoryService service;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Response post(String body) {
        logger.debug("adding a new category with body {}", body);

        Category category =

        return null;
    }

}
