package org.example.repository;

import org.example.dao.Actor;
import org.example.dao.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActorRepository extends JpaRepository<Actor, String> {

    @Query(value = "select * FROM actor d WHERE d.primaryName LIKE %:name%",
            countQuery = "select count(*) FROM actor d WHERE d.primaryName LIKE %:name%",
            nativeQuery = true)
    List<Actor> findByNameLike(@Param("name") String name);

}
