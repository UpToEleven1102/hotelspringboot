package com.example.ec.explorecalifornia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Huyen VU on 8/18/2017.
 */
@Entity
public class TourPackage implements Serializable{
    @Id
    private String code;

    @Column
    private String name;

    public TourPackage(){}

    public TourPackage(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code,name);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(obj == null|| getClass() != obj.getClass())
            return false;

        TourPackage tourPackage = (TourPackage)obj;

        return Objects.equals(tourPackage.code,this.code)
                &&Objects.equals(tourPackage.name,this.name);
    }

    @Override
    public String toString() {
        return "TourPackage{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}


/*

package com.example.ec.explorecalifornia.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Classification of Tours.
 *
 * Created by Mary Ellen Bowman
 */
/*
@Entity
public class TourPackage implements Serializable {
    @Id
    private String code;

    @Column
    private String name;

    protected TourPackage() {
    }

    public TourPackage(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TourPackage{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourPackage that = (TourPackage) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }
}

 */