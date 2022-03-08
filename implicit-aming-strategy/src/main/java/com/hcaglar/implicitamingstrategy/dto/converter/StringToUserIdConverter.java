package com.hcaglar.implicitamingstrategy.dto.converter;

import com.hcaglar.implicitamingstrategy.exception.InvalidUuidException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
@Component
public class StringToUserIdConverter implements Converter<String, UUID> {
    @Override
    public UUID convert(String uuid) {
        final var id = Optional
                .ofNullable(uuid)
                .orElseThrow(IllegalAccessError::new);

        if (isUUID(id))
            return UUID.fromString(id);

        throw new InvalidUuidException("Invalid uuid, please check.");
    }

    private boolean isUUID(String uuid) {
        final var length  = uuid.length();

        if (length > 36)
            return false;

        int dash1 = uuid.indexOf(45, 0);
        int dash2 = uuid.indexOf(45, dash1 + 1);
        int dash3 = uuid.indexOf(45, dash2 + 1);
        int dash4 = uuid.indexOf(45, dash3 + 1);
        int dash5 = uuid.indexOf(45, dash4 + 1);

        if (dash4 >= 0 && dash5 < 0)
            return true;
        return false;
    }
}
