package com.solodovnikov.service;

import java.util.List;

public interface AbstractService<E, ID> {

    List<E> getAll();

    E getById(ID id);

    E create(E newObject);

    E update(ID id, E object);

    Boolean deleteById(ID id);

}
