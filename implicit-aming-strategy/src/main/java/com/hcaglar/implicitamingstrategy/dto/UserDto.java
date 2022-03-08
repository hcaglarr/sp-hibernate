package com.hcaglar.implicitamingstrategy.dto;

import com.hcaglar.implicitamingstrategy.entity.BaseEntity;

import java.util.UUID;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
public class UserDto extends BaseEntity {
    private String m_firstName;
    private String m_lastName;

    public UserDto(){}

    public UserDto(String firstName, String lastName) {
        this(null, firstName, lastName);
    }

    public UserDto(UUID uuid, String firstName, String lastName) {
        super.setId(uuid);
        m_firstName = firstName;
        m_lastName = lastName;
    }

    public String getFirstName() {
        return m_firstName;
    }

    public void setFirstName(String firstName) {
        m_firstName = firstName;
    }

    public String getLastName() {
        return m_lastName;
    }

    public void setLastName(String lastName) {
        m_lastName = lastName;
    }
}
