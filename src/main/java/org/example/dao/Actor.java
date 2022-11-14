package org.example.dao;

import javax.persistence.*;

@Entity
@Table(name = "actor")
public class Actor {

    private String nConst;
    private String primaryName;
    private int birthYear;
    private int deathYear;

    public Actor() {

    }

    public Actor(String nConst, String primaryName, int birthYear, int deathYear) {
        this.nConst = nConst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    @Id
    public String getnConst() {
        return nConst;
    }

    public void setnConst(String nConst) {
        this.nConst = nConst;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

}
