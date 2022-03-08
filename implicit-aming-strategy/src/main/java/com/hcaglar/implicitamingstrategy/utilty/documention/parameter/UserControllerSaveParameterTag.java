package com.hcaglar.implicitamingstrategy.utilty.documention.parameter;

import com.hcaglar.implicitamingstrategy.dto.UserDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 8.03.2022
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Parameter(schema = @Schema(implementation = UserDto.class))
public @interface UserControllerSaveParameterTag {
}
