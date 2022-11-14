package org.example.dao;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "appearance")
public class Appearance {

    private String appearanceId;
    private String nconst_f;
    private String tconst_f;
    private String characters;
    private Movie movieLite;

    public Appearance(String appearanceId, String nconst_f, String tconst_f, String characters) {
        this.appearanceId = appearanceId;
        this.nconst_f = nconst_f;
        this.tconst_f = tconst_f;
        this.characters = characters;
    }

    public Appearance() {

    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getAppearanceId() {
        return appearanceId;
    }

    public void setAppearanceId(String appearanceId) {
        this.appearanceId = appearanceId;
    }


    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public String getNconst_f() {
        return nconst_f;
    }

    public void setNconst_f(String nconst_f) {
        this.nconst_f = nconst_f;
    }

    public String getTconst_f() {
        return tconst_f;
    }

    public void setTconst_f(String tconst_f) {
        this.tconst_f = tconst_f;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "nconst_f", updatable = false, insertable = false)
    public Movie getMovieLite() {
        return movieLite;
    }

    public void setMovieLite(Movie movieLite) {
        this.movieLite = movieLite;
    }

}
