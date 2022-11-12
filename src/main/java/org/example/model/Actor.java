package org.example.model;

public class Actor {

    private String id;
    private String name;

    public Actor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Actor toActor(org.example.dao.Actor actor) {
        return new Actor(actor.getnConst(), actor.getPrimaryName());
    }


}
