package org.example.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "appearance")
public class Appearance {

    private String appearanceId;
    private String nConst;
    private String tConst;
    private String characters;

    public Appearance(String appearanceId, String nConst, String tConst, String characters) {
        this.appearanceId = appearanceId;
        this.nConst = nConst;
        this.tConst = tConst;
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


    public String getnConst() {
        return nConst;
    }

    public void setnConst(String nConst) {
        this.nConst = nConst;
    }

    public String gettConst() {
        return tConst;
    }

    public void settConst(String tConst) {
        this.tConst = tConst;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }


}
