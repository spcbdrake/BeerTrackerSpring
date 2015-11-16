package com.theironyard.services;

import com.theironyard.entities.Beer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BeerRepository extends CrudRepository<Beer, Integer> {
    List<Beer> findByType(String type);
    List<Beer> findByTypeAndCalories (String type, Integer calories);
    List<Beer> findByTypeAndCaloriesIsLessThanEqual (String type, Integer calories);

    Beer findFirstByType(String type);
    int countByType(String type);
    List<Beer> findByTypeOrderByNameAsc(String type);

    @Query("SELECT b FROM Beer b WHERE LOWER(name) LIKE '%' || LOWER(?) || '%'")
    List<Beer> searchByName(String name);
}
