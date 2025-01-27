package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;

import java.util.List;

public interface ServiceInterface {
    List<WasteCategoryDTO> getAllCategories();

    WasteCategoryDTO getCategoryById(Long id);

    WasteCategoryDTO createCategory(WasteCategoryDTO categoryDTO); //

    WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO updatedCategoryDTO);

    void deleteCategory(Long id);

    void deleteAllCategories();
}
