package org.example.repository;

import org.example.dao.Movie;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieRepository extends JpaRepository<Movie, String> {

    @Query(value = "select * FROM movie d WHERE d.primaryTitle LIKE :name",
            countQuery = "select count(*) FROM movie d WHERE d.primaryTitle LIKE :name",
            nativeQuery = true)
    List<Movie> findByNameLike(PageRequest pageRequest, @Param("name") String title);
}
