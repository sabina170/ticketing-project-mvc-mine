package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface CrudService<T,U> {

    T save(T user);
    T findById(U username);
    List<T> findAll();
    void deleteById(U username);

}
