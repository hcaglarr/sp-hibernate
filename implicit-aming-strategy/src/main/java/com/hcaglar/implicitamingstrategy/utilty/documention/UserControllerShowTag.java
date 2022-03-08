package com.hcaglar.implicitamingstrategy.utilty.documention;

import com.hcaglar.implicitamingstrategy.entity.User;
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
@Tag(
        name = "user#show",
        description = "If there is a user at the specified id, to fetch")
@Operation(
        summary = "Bring the person with the Id.",
        method = "GET")
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "The user has been successfully brought in.",
                content = {@Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = User.class))}),
        @ApiResponse(
                responseCode = "404",
                description = "user not found",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
})
public @interface UserControllerShowTag {
}
