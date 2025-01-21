package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.entity.WasteCategory;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.exception.CategoryNotFoundException;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.wasteCategoryMapper.WasteCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceImplementation implements ServiceInterface {

    private final WasteCategoryRepository wasteCategoryRepository;

    public ServiceImplementation(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }


    @Override
    public List<WasteCategoryDTO> getAllCategories() {
        List<WasteCategory> wasteCategories = wasteCategoryRepository.findAll();
        return wasteCategories.stream()
                .map(WasteCategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WasteCategoryDTO getCategoryById(Long id) {
        WasteCategory wasteCategory = wasteCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id: " + id + " not found"));
        return WasteCategoryMapper.toDTO(wasteCategory);
    }

    @Override
    public WasteCategoryDTO createCategory(WasteCategoryDTO categoryDTO) {
        WasteCategory wasteCategory = WasteCategoryMapper.toEntity(categoryDTO);
        WasteCategory savedWasteCategory = wasteCategoryRepository.save(wasteCategory);
        return WasteCategoryMapper.toDTO(savedWasteCategory);
    }

    @Override
    public WasteCategoryDTO updateCategory(Long id, WasteCategoryDTO updatedCategoryDTO) {
        WasteCategory existingCategory = wasteCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category by id: " + id + " not found"));
        existingCategory.setName(updatedCategoryDTO.getName());
        existingCategory.setDisposalGuidelines(updatedCategoryDTO.getDisposalGuidelines());
        existingCategory.setRecyclingTips(updatedCategoryDTO.getRecyclingTips());
        WasteCategory updatedCategory = wasteCategoryRepository.save(existingCategory);
        return WasteCategoryMapper.toDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        boolean exists = wasteCategoryRepository.existsById(id);
        if (!exists) {
            throw new CategoryNotFoundException("Category by id: " + id + " not found");
        }
        wasteCategoryRepository.deleteById(id);
    }

    @Override
    public void deleteAllCategories() {
        wasteCategoryRepository.deleteAll();
    }
}
