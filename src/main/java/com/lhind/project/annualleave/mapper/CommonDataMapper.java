package com.lhind.project.annualleave.mapper;

import java.util.List;

public interface CommonDataMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}