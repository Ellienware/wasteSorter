package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.repository;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.entity.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long>{
    boolean existsByName(String name);

}
