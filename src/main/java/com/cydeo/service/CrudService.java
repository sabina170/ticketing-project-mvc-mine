package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface CrudService<T,U> {

    T save(T object);
    T findById(U u);
    List<T> findAll();
    void deleteById(U u);

}
