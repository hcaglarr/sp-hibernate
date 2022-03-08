package com.hcaglar.implicitamingstrategy.service;

import com.hcaglar.implicitamingstrategy.dto.UserDto;
import com.hcaglar.implicitamingstrategy.dto.converter.UserConverter;
import com.hcaglar.implicitamingstrategy.entity.User;
import com.hcaglar.implicitamingstrategy.exception.EntityNotFoundException;
import com.hcaglar.implicitamingstrategy.exception.UserNotFoundException;
import com.hcaglar.implicitamingstrategy.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 4.03.2022
 */
@Service
public class UserService {

    @Value("${exception.user.not-found}")
    private String notFound;
    private final UserRepo m_userRepo;

    public UserService(UserRepo userRepo) {
        m_userRepo = userRepo;
    }

    public Iterable<User> findAll(){
         return m_userRepo.findAll();
    }

    public User findById(UUID id) {
        final var uuid = Optional
                .ofNullable(id)
                .orElseThrow(IllegalArgumentException::new);

        return m_userRepo
                .findById(uuid)
                .orElseThrow(() -> new UserNotFoundException(notFound));
    }

    public List<String> findAllByColumnName(){
        return Collections.unmodifiableList(m_userRepo.findAllByColumnName());
    }

    public List<String> findAllByTableName(){
        return Collections.unmodifiableList(m_userRepo.findAllByTableName());
    }

    public URI save(UserDto userDto) {
        final var user =  Optional.ofNullable(userDto)
                .orElseThrow(() -> new EntityNotFoundException("Check Again."));

        final var translatedUser = UserConverter.INSTANCE.convert(user);
        final var newUser = m_userRepo.save(translatedUser);
        final var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+ newUser.getId().toString())
                .build()
                .toUri();

        return uri;
    }
}
