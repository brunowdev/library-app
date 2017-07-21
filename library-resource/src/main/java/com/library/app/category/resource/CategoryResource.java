package com.library.app.category.resource;

import com.library.app.category.model.Category;
import com.library.app.category.services.CategoryService;
import com.library.app.common.json.JsonUtils;
import com.library.app.common.json.OperationResultJsonWriter;
import com.library.app.common.model.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * Created by BRUNO-PC on 07/05/2017.
 */
public class CategoryResource {

    CategoryService service;
    CategoryJsonConverter converter;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Response post(String body) {
        logger.debug("adding a new category with body {}", body);

        Category category = converter.convertFrom(body);

        category = service.add(category);
        final OperationResult result = OperationResult.success(JsonUtils.getJsonElementWithId(category.getId()));

        logger.debug("Returning the operation result after adding category {}", result);
        return Response.status(Response.Status.CREATED)
                .entity(OperationResultJsonWriter.toJson(result))
                .build();
    }

}
