package com.hcaglar.implicitamingstrategy.utilty.documention;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Tag(name = "user#save", description = "save user")
@Operation(summary = "save user")
@ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "successfully saved..",
                content = {@Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(hidden = true))}
        ),
        @ApiResponse(
                responseCode = "404",
                description = "entity not found",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
})
public @interface UserControllerSaveTag {
}
