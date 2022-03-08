package com.hcaglar.implicitamingstrategy.utilty.documention;

import com.hcaglar.implicitamingstrategy.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@Tag(name = "user#index", description = "all users" )
@Operation(summary = "fetch all users.")
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Users successfully brought.",
                content = {@Content( mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = User.class)))}
        )
})
public @interface UserControllerIndexTag {
}
