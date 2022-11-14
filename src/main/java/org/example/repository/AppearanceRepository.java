package org.example.repository;

import org.example.dao.Appearance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppearanceRepository extends JpaRepository<Appearance, String> {

    @Query(value = "select * FROM appearance d WHERE d.tconst_f LIKE %:actorId%",
            countQuery = "select count(*) FROM appearance d WHERE d.tconst_f LIKE %:actorId%",
            nativeQuery = true)
    List<Appearance> findByActorId(@Param("actorId") String actorId);
}
