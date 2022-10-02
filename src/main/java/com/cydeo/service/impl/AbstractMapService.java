package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService <T,U> {

    //T- represents object type, U -represents id (for RoleDTO), username (for UserDTO)

    public Map<U, T> map = new HashMap<>();  // this is my Custom DB

    T save( U u, T object){
        map.put(u, object);
        return object;
    }

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    T findById (U u){
        return map.get(u);
    }

    void deleteById(U u){
        map.remove(u);
    }

    void update(U u, T object){
        map.put(u, object);
    }



}
