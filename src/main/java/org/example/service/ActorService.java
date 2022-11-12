package org.example.service;

import org.example.model.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> getAll(int page, int size, String nameWildCard);

    Actor getById(String id);
}
