package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface {
    List<WasteCategoryDTO> getAllCategories();

    WasteCategoryDTO getCategoryById(Long id); // Returns an Optional

    WasteCategoryDTO createCategory(WasteCategoryDTO categoryDTO); // Renamed for clarity

    WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO updatedCategoryDTO); // Returns the updated category

    void deleteCategory(Long id);

    void deleteAllCategories();
}
