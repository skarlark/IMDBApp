package org.example.repository;

import org.example.dao.Appearance;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppearanceRepository extends JpaRepository<Appearance, String> {
}
