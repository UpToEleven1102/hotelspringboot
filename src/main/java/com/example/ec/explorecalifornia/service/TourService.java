package com.example.ec.explorecalifornia.service;

import com.example.ec.explorecalifornia.domain.Difficulty;
import com.example.ec.explorecalifornia.domain.Region;
import com.example.ec.explorecalifornia.domain.Tour;
import com.example.ec.explorecalifornia.domain.TourPackage;
import com.example.ec.explorecalifornia.repository.TourPackageRepository;
import com.example.ec.explorecalifornia.repository.TourRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Huyen VU on 8/18/2017.
 */
@Service
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description, String blurb, int price, String duration, String bullets, String keywords, String packageType, Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(packageType);

        if(tourPackage == null)
            throw new RuntimeException("No such tour package:" +packageType);

        return tourRepository.save(new Tour(title,description,blurb,price,duration,bullets,keywords,tourPackage,difficulty,region));
    }

    public Iterable<Tour> loopUp(){
        return tourRepository.findAll();
    }

    public long total(){
        return tourRepository.count();
    }

}
