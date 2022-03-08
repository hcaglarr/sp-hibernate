package com.hcaglar.implicitamingstrategy.entity;

import javax.persistence.Entity;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 3.03.2022
 */
@Entity
public class User extends BaseEntity {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
