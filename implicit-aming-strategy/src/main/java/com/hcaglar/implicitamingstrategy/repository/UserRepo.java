package com.hcaglar.implicitamingstrategy.repository;

import com.hcaglar.implicitamingstrategy.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
@Repository
public interface UserRepo extends CrudRepository<User, UUID> {

    @Query(value = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS", nativeQuery = true)
    List<String> findAllByTableName();
    @Query(value = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME= 'USER'", nativeQuery = true)
    List<String> findAllByColumnName();
}
