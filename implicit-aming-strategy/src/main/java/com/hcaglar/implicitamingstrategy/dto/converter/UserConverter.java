package com.hcaglar.implicitamingstrategy.dto.converter;

import com.hcaglar.implicitamingstrategy.dto.UserDto;
import com.hcaglar.implicitamingstrategy.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    User convert(UserDto userDto);
    UserDto convert(User user);
}
