package com.hcaglar.implicitamingstrategy.utilty.documention;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 8.03.2022
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag(
        name = "column#index",
        description = "Entities, column names of tables created by JPA Hibernate")
@Operation(
        summary = "fetch column names",
        method = "GET")
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Successful",
                content = {@Content( mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(
                responseCode = "404",
                description = "entity not found",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
})
public @interface UserControllerFindByColumnNameTag {
}
