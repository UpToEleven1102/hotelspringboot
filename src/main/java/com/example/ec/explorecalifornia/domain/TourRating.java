package com.example.ec.explorecalifornia.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Huyen VU on 8/19/2017.
 */
@Entity
public class TourRating implements Serializable{
    @EmbeddedId
    private TourRatingPK pk;

    @Column(nullable = false)
    private int score;

    @Column
    private String comment;

    public TourRating(){}

    public TourRating(TourRatingPK tourRatingPK, int score, String comment){
        this.pk = tourRatingPK;
        this.score = score;
        this.comment = comment;
    }

    public TourRatingPK getTourRatingPK() {
        return pk;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk,score,comment);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj==null||getClass()!= obj.getClass())
            return false;

        TourRating tourRating = (TourRating)obj;

        return this.pk.equals(tourRating.pk)
                && this.score == tourRating.score
                &&this.comment.equals(tourRating.comment);
    }

    @Override
    public String toString() {
        return "Tour Rating: {"
                +"TourRatingPk: "+pk +'\''
                +"Score: "+score +'\''
                +"Comment: "+comment+'\''
                +"}";
    }
}
