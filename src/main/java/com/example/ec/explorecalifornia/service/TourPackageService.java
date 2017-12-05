package com.example.ec.explorecalifornia.service;

import com.example.ec.explorecalifornia.domain.TourPackage;
import com.example.ec.explorecalifornia.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Huyen VU on 8/18/2017.
 */
@Service
public class TourPackageService {
    private TourPackageRepository _repo;

    @Autowired
    public TourPackageService(TourPackageRepository _repo) {
        this._repo = _repo;
    }

    public TourPackage createTourPackage(String code, String name){
        if(_repo.exists(code))
            return null;

        return _repo.save(new TourPackage(code, name));
    }

    public Iterable<TourPackage> lookUp(){
        return _repo.findAll();
    }

    public long total(){
        return _repo.count();
    }
}

