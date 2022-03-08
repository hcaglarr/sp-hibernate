package com.hcaglar.implicitamingstrategy.controller;

import com.hcaglar.implicitamingstrategy.dto.converter.StringToUserIdConverter;
import com.hcaglar.implicitamingstrategy.dto.UserDto;
import com.hcaglar.implicitamingstrategy.entity.User;
import com.hcaglar.implicitamingstrategy.service.UserService;
import com.hcaglar.implicitamingstrategy.utilty.documention.*;
import com.hcaglar.implicitamingstrategy.utilty.documention.parameter.UserControllerSaveParameterTag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService m_userService;
    private final StringToUserIdConverter m_idConverter;

    public UserController(UserService userService, StringToUserIdConverter idConverter) {
        m_userService = userService;
        m_idConverter = idConverter;
    }

    @GetMapping
    @UserControllerIndexTag
    public ResponseEntity<Iterable<User>> index(){
        final var userList = m_userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{uuid}")
    @UserControllerShowTag
    public ResponseEntity<User> show(@PathVariable String uuid){
        final var user = m_userService.findById(m_idConverter.convert(uuid));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/column-name")
    @UserControllerFindByColumnNameTag
    public ResponseEntity<List<String>> findByColumnName(){
        final var columnNames = m_userService.findAllByColumnName();
        return ResponseEntity.ok(columnNames);
    }

    @GetMapping("/table-name")
    @UserControllerFindByTableNameTag
    public ResponseEntity<List<String>> findByTableName(){
        final var tableNames = m_userService.findAllByTableName();
        return ResponseEntity.ok(tableNames);
    }

    @PostMapping
    @UserControllerSaveTag
    public ResponseEntity<HttpEntity> save(@UserControllerSaveParameterTag @RequestBody UserDto userDto){
        final var user = m_userService.save(userDto);
        final var httpHeaders = new HttpHeaders();
        httpHeaders.add("user", user.toString());

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).build();
    }
}
