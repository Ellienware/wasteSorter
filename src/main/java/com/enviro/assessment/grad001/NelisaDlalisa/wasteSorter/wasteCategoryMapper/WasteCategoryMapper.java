package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.wasteCategoryMapper;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.entity.WasteCategory;

public class WasteCategoryMapper {

    public static WasteCategory toEntity(WasteCategoryDTO wasteCategoryDTO) {
        return new WasteCategory(
                wasteCategoryDTO.getId(),
                wasteCategoryDTO.getName(),
                wasteCategoryDTO.getDisposalGuidelines(),
                wasteCategoryDTO.getRecyclingTips() // Correct mapping
        );
    }

    public static WasteCategoryDTO toDTO(WasteCategory wasteCategory) {
        return new WasteCategoryDTO(
                wasteCategory.getId(),
                wasteCategory.getName(),
                wasteCategory.getDisposalGuidelines(),
                wasteCategory.getRecyclingTips() // Correct mapping
        );
    }
}

