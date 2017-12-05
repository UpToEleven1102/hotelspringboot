package com.example.ec.explorecalifornia.web;

import android.media.Rating;
import com.example.ec.explorecalifornia.domain.Tour;
import com.example.ec.explorecalifornia.domain.TourRating;
import com.example.ec.explorecalifornia.domain.TourRatingPK;
import com.example.ec.explorecalifornia.repository.TourRatingRepository;
import com.example.ec.explorecalifornia.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Created by Huyen VU on 8/19/2017.
 */

@RestController
@RequestMapping(path = "tours/{tourId}/ratings")
public class TourRatingController {
    private TourRepository _repoTour;
    private TourRatingRepository _repoTourRating;

    public TourRatingController(){}



    @Autowired
    public TourRatingController(TourRepository _repoTour, TourRatingRepository _repoTourRating) {
        this._repoTour = _repoTour;
        this._repoTourRating = _repoTourRating;
    }
    //Action methods -------------------------

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createTourRating(@PathVariable(value = "tourId")int tourId, @RequestBody @Validated RatingDto dto){
        Tour tour = verifyTour(tourId);
        _repoTourRating.save(new TourRating(new TourRatingPK(tour,dto.getCustomerId()),dto.getScore(),dto.getComment()));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Page<RatingDto> getAllTourRatings(@PathVariable(value = "tourId")int tourId,Pageable pageable){
        verifyTour(tourId);

        Page<TourRating> tourRatings = _repoTourRating.findByPkTourId(tourId,pageable);
        List<RatingDto> ratingDtos = tourRatings.getContent().stream().map(t-> toDto(t)).collect(Collectors.toList());

        return new PageImpl<RatingDto>(ratingDtos,pageable,tourRatings.getTotalPages());
    }

    @RequestMapping(method = RequestMethod.GET,path = "/average")
    public AbstractMap.SimpleEntry<String,Double> getScoreAverage(@PathVariable(value = "tourId")int tourId){
        verifyTour(tourId);
        OptionalDouble average = _repoTourRating.findByPkTourId(tourId).stream().mapToInt(TourRating::getScore).average();

        return new AbstractMap.SimpleEntry<String, Double>("average",average.isPresent()?average.getAsDouble():null);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RatingDto updateWithPut(@PathVariable(value = "tourId")int tourId,@RequestBody @Validated RatingDto dto){
        TourRating tourRating = verifyTourRating(tourId, dto.getCustomerId());

        tourRating.setComment(dto.getComment());
        tourRating.setScore(dto.getScore());

        return toDto(_repoTourRating.save(tourRating));
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public RatingDto updateWithPatch(@PathVariable(value = "tourId")int tourId, @RequestBody @Validated RatingDto dto){
        TourRating tourRating = verifyTourRating(tourId,dto.getCustomerId());

        if(dto.getComment()!=null)
            tourRating.setComment(dto.getComment());

        if(dto.getScore()!=null)
            tourRating.setScore(dto.getScore());

        return toDto(_repoTourRating.save(tourRating));
    }

    @RequestMapping(method = RequestMethod.DELETE,path = "/{customerId}")
    public void delete(@PathVariable(value = "tourId")int tourId, @PathVariable(value = "customerId")int customerId){
        TourRating tourRating  = verifyTourRating(tourId,customerId);
        _repoTourRating.delete(tourRating);
    }

    //Helper methods--------------------
    public RatingDto toDto(TourRating tourRating)
    {
        return new RatingDto(tourRating.getScore(),tourRating.getTourRatingPK().getCustomerId(),tourRating.getComment());
    }

    public Tour verifyTour(int tourId){
        Tour tour = _repoTour.findOne(tourId);

        if(tour == null)
            throw new NoSuchElementException("Tour does not exist :"+tourId);

        return tour;
    }


    public TourRating verifyTourRating(int tourId, int customerId){
        TourRating tourRating = _repoTourRating.findByPkTourIdAndPkCustomerId(tourId,customerId);
        if(tourRating == null)
            throw new NoSuchElementException("TourRating does not exist : tourId :"+tourId + " customerId : "+ customerId);
        return tourRating;
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404NotFound(NoSuchElementException ex){
        return ex.getMessage();
    }
}
