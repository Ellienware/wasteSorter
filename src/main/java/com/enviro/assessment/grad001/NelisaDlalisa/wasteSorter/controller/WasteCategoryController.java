package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.controller;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service.ServiceImplementation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {

    private static final Logger logger = LoggerFactory.getLogger(WasteCategoryController.class);
    private final ServiceImplementation implementation;

    public WasteCategoryController(ServiceImplementation implementation) {
        this.implementation = implementation;
    }

    @PostMapping
    public ResponseEntity<WasteCategoryDTO> createCategory(@Valid @RequestBody WasteCategoryDTO wasteCategoryDTO) {
        logger.info("Creating a new waste category: {}", wasteCategoryDTO.getName());
        WasteCategoryDTO savedCategory = implementation.createCategory(wasteCategoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> getCategory(@PathVariable @Positive(message = "ID must be positive") Long id) {
        logger.info("Fetching category with id: {}", id);
        WasteCategoryDTO wasteCategoryDTO = implementation.getCategoryById(id);
        return ResponseEntity.ok(wasteCategoryDTO);
    }

    @GetMapping
    public ResponseEntity<List<WasteCategoryDTO>> getAllCategories() {
        logger.info("Fetching all waste categories");
        List<WasteCategoryDTO> categories = implementation.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> updateCategory(
            @PathVariable @Positive(message = "ID must be positive") Long id,
            @Valid @RequestBody WasteCategoryDTO updatedCategoryDTO) {
        logger.info("Updating category with id: {}", id);
        WasteCategoryDTO updatedCategory = implementation.updateCategory(id, updatedCategoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable @Positive(message = "ID must be positive") Long id) {
        logger.info("Deleting category with id: {}", id);
        implementation.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCategories() {
        logger.info("Deleting all waste categories");
        implementation.deleteAllCategories();
        return ResponseEntity.noContent().build();
    }
}

