package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@NoArgsConstructor
public class WasteCategoryDTO {

    public WasteCategoryDTO(Long id, String name, String disposalGuidelines, String recyclingTips) {
        this.id = id;
        this.name = name;
        this.disposalGuidelines = disposalGuidelines;
        this.recyclingTips = recyclingTips;
    }

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Disposal guidelines are required")
    private String disposalGuidelines;

    @NotBlank(message = "Recycling tips are required")
    private String recyclingTips;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisposalGuidelines() {
        return disposalGuidelines;
    }

    public void setDisposalGuidelines(String disposalGuidelines) {
        this.disposalGuidelines = disposalGuidelines;
    }

    public String getRecyclingTips() {
        return recyclingTips;
    }

    public void setRecyclingTips(String recyclingTips) {
        this.recyclingTips = recyclingTips;
    }
}
