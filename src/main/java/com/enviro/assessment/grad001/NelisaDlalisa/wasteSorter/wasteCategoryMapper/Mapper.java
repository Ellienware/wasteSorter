package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.wasteCategoryMapper;


import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.entity.WasteCategory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public static WasteCategory toEntity(WasteCategoryDTO wasteCategoryDTO) {
        if (wasteCategoryDTO == null) return null;
        return new WasteCategory(
                wasteCategoryDTO.getId(),
                wasteCategoryDTO.getName(),
                wasteCategoryDTO.getDisposalGuidelines(),
                wasteCategoryDTO.getRecyclingTips()
        );
    }

    public static WasteCategoryDTO toDTO(WasteCategory wasteCategory) {
        if (wasteCategory == null) return null;
        return new WasteCategoryDTO(
                wasteCategory.getId(),
                wasteCategory.getName(),
                wasteCategory.getDisposalGuidelines(),
                wasteCategory.getRecyclingTips()
        );
    }
}


