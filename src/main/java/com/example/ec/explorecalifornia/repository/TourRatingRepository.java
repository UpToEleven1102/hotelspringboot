package com.example.ec.explorecalifornia.repository;

import com.example.ec.explorecalifornia.domain.TourRating;
import com.example.ec.explorecalifornia.domain.TourRatingPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Huyen VU on 8/19/2017.
 */
@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPK> {
    List<TourRating> findByPkTourId(@Param("tourId")Integer id);

    Page<TourRating> findByPkTourId(@Param("tourId")Integer id,Pageable pageable);

    TourRating findByPkTourIdAndPkCustomerId(@Param("tourId")Integer tourId, @Param("customerId")Integer customerId);
}
