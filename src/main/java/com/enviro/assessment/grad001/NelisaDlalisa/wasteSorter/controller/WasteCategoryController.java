package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.controller;

import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.exception.CategoryNotFoundException;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service.ServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {

    private final ServiceImplementation implementation;

    public WasteCategoryController(ServiceImplementation implementation) {
        this.implementation = implementation;
    }


    @PostMapping
    public ResponseEntity<WasteCategoryDTO> createCategory(@Valid @RequestBody WasteCategoryDTO wasteCategoryDTO) {
        WasteCategoryDTO savedCategory = implementation.createCategory(wasteCategoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<WasteCategoryDTO> getCategory(@PathVariable Long id) {
        WasteCategoryDTO wasteCategoryDTO = implementation.getCategoryById(id);
        return ResponseEntity.ok(wasteCategoryDTO);
    }

    @GetMapping
    public ResponseEntity<List<WasteCategoryDTO>> getAllCategories() {
        List<WasteCategoryDTO> categories = implementation.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @PutMapping("/{id}")
    public ResponseEntity<WasteCategoryDTO> updateCategory(@PathVariable Long id, @RequestBody WasteCategoryDTO updatedCategoryDTO) {
        WasteCategoryDTO updatedCategory = implementation.updateCategory(id, updatedCategoryDTO);
        return ResponseEntity.ok(updatedCategory);  // Returning the updated category
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        try {
            implementation.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (CategoryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllCategories() {
        implementation.deleteAllCategories();
        return ResponseEntity.noContent().build();
    }
}




