package org.example.service;

import org.example.model.Actor;
import org.example.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<Actor> getAll(int page, int size, String nameWildCard) {
        if (size > 1000)
            throw new IllegalArgumentException("Page Size cannot be greater than 1000");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("primaryName"));
        List<Actor> actors = new ArrayList<>();
        boolean isValidWildcard = false;
        if (isValidWildcard) {
            actorRepository.findByNameLike(nameWildCard).forEach(actor -> actors.add(Actor.toActor(actor)));
        } else {
            actorRepository.findAll(pageRequest).get().forEach(actor -> actors.add(Actor.toActor(actor)));
        }
        return actors;
    }

    @Override
    public Actor getById(String id) {
        return Actor.toActor(actorRepository.findById(id).get());
    }
}
