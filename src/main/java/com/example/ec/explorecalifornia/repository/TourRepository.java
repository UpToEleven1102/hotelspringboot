package com.example.ec.explorecalifornia.repository;

import com.example.ec.explorecalifornia.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Huyen VU on 8/18/2017.
 */
@Repository
public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {
    Page<Tour> findByTourPackageCode(@Param("code")String code, Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends Tour> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Tour> Iterable<S> save(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Tour entity);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends Tour> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
