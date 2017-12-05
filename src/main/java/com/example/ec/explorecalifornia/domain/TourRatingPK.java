package com.example.ec.explorecalifornia.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Huyen VU on 8/19/2017.
 */
@Embeddable
public class TourRatingPK implements Serializable{
    @ManyToOne
    private Tour tour;

    @Column(insertable = false,updatable = false, nullable = false)
    private int customerId;

    public TourRatingPK(){}

    public TourRatingPK(Tour tour, int customerId){
        this.tour = tour;
        this.customerId= customerId;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tour,customerId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this ==obj)
            return true;
        if(obj==null||getClass() != obj.getClass()){
            return false;
        }

        TourRatingPK that = (TourRatingPK)obj;

        return this.tour.equals(that.tour)
                &&this.customerId==(that.customerId);
    }

    @Override
    public String toString() {
        return "TourRatingPk {"
                +"tour: "+tour+ '\''
                +"Customer Id: " + customerId + '\''
                +"}";
    }
}
