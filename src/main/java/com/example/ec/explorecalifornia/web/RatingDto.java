package com.example.ec.explorecalifornia.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Huyen VU on 8/19/2017.
 */
public class RatingDto {
    @Min(0)
    @Max(5)
    private Integer score;

    @NotNull
    private int customerId;

    @Size(max = 255)
    private String comment;

    public RatingDto(){}

    public RatingDto(int score, int customerId, String comment) {
        this.score = score;
        this.customerId = customerId;
        this.comment = comment;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
