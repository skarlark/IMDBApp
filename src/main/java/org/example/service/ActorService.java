package org.example.service;

import org.example.dao.Appearance;
import org.example.model.Actor;
import org.example.throttling.APIThrottling;

import java.util.List;

public interface ActorService extends APIThrottling {

    List<Actor> getAll(int page, int size, String nameWildCard);

    Actor getById(String id);
}
