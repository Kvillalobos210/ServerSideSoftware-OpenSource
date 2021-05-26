package com.appdhome.repository;

import com.appdhome.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICityRepository extends JpaRepository<City, Long> {
    public City findByName(String name);
}
