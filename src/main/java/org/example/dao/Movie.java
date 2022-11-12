package org.example.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {

    private String tConst;
    private String type;
    private String primaryTitle;
    private String originalTitle;
    private boolean adult;
    private int startYear;
    private int endYear;
    private int runtimeInMin;


    public Movie() {

    }

    public Movie(String tConst, String type, String primaryTitle, String originalTitle, boolean adult, int startYear, int endYear, int runtimeInMin) {
        this.tConst = tConst;
        this.type = type;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.adult = adult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeInMin = runtimeInMin;
    }



    @Id
    public String gettConst() {
        return tConst;
    }

    public void settConst(String tConst) {
        this.tConst = tConst;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Column(columnDefinition = "TINYINT(1)")
    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getRuntimeInMin() {
        return runtimeInMin;
    }

    public void setRuntimeInMin(int runtimeInMin) {
        this.runtimeInMin = runtimeInMin;
    }

}
