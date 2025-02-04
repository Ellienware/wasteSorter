package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.entity.WasteCategory;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.exception.CategoryNotFoundException;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.wasteCategoryMapper.Mapper;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImplementation implements ServiceInterface {

    private final WasteCategoryRepository wasteCategoryRepository;

    public ServiceImplementation(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    @Override
    public List<WasteCategoryDTO> getAllCategories() {
        return wasteCategoryRepository.findAll().stream()
                .map(Mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WasteCategoryDTO getCategoryById(Long id) {
        WasteCategory wasteCategory = wasteCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id: " + id + " not found"));
        return Mapper.toDTO(wasteCategory);
    }
    @Override
    @Transactional
    public WasteCategoryDTO createCategory(WasteCategoryDTO categoryDTO) {
        if (categoryDTO == null || categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (wasteCategoryRepository.existsByName(categoryDTO.getName())) {
            throw new EntityExistsException("Category with name '" + categoryDTO.getName() + "' already exists");
        }
        WasteCategory wasteCategory = Mapper.toEntity(categoryDTO);
        WasteCategory savedCategory = wasteCategoryRepository.save(wasteCategory);
        return Mapper.toDTO(savedCategory);
    }


    @Override
    @Transactional
    public WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO updatedCategoryDTO) {
        WasteCategory existingCategory = wasteCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id: " + id + " not found"));
        existingCategory.setName(updatedCategoryDTO.getName());
        existingCategory.setDisposalGuidelines(updatedCategoryDTO.getDisposalGuidelines());
        existingCategory.setRecyclingTips(updatedCategoryDTO.getRecyclingTips());
        return Mapper.toDTO(wasteCategoryRepository.save(existingCategory));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if (!wasteCategoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category by id: " + id + " not found");
        }
        wasteCategoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllCategories() {
        wasteCategoryRepository.deleteAll();
    }
}
