package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.controllerTests;


import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.controller.WasteCategoryController;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service.ServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

class WasteCategoryControllerTest {

    @Mock
    private ServiceImplementation service;

    private WasteCategoryController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new WasteCategoryController(service);
    }

    @Test
    void testCreateCategory() {
        WasteCategoryDTO categoryDTO = new WasteCategoryDTO(1L, "Metal", "Place in metal recycling bins.", "Remove any attached non-metal parts before recycling.");
        when(service.createCategory(categoryDTO)).thenReturn(categoryDTO);
        ResponseEntity<WasteCategoryDTO> response = controller.createCategory(categoryDTO);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Metal", response.getBody().getName());
    }

    @Test
    void testGetCategory() {
        WasteCategoryDTO categoryDTO = new WasteCategoryDTO(1L, "Electronics", "Drop off at e-waste recycling centers.", "Do not throw electronics in regular bins; remove batteries where applicable.");
        when(service.getCategoryById(1L)).thenReturn(categoryDTO);
        ResponseEntity<WasteCategoryDTO> response = controller.getCategory(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Electronics", response.getBody().getName());
    }

    @Test
    void testGetAllCategories() {
        WasteCategoryDTO category1 = new WasteCategoryDTO(1L, "Metal", "Place in metal recycling bins.", "Remove any attached non-metal parts before recycling.");
        WasteCategoryDTO category2 = new WasteCategoryDTO(2L, "Electronics", "Drop off at e-waste recycling centers.", "Do not throw electronics in regular bins; remove batteries where applicable.");
        when(service.getAllCategories()).thenReturn(List.of(category1, category2));
        ResponseEntity<List<WasteCategoryDTO>> response = controller.getAllCategories();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testUpdateCategory() {
        WasteCategoryDTO updatedCategoryDTO = new WasteCategoryDTO(1L, "Electronics", "Drop off at e-waste recycling centers.", "Do not throw electronics in regular bins; remove batteries where applicable.");
        when(service.updateCategory(1L, updatedCategoryDTO)).thenReturn(updatedCategoryDTO);
        ResponseEntity<WasteCategoryDTO> response = controller.updateCategory(1L, updatedCategoryDTO);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Drop off at e-waste recycling centers.", response.getBody().getDisposalGuidelines());
    }

    @Test
    void testDeleteCategory() {
        doNothing().when(service).deleteCategory(1L);
        ResponseEntity<Void> response = controller.deleteCategory(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteAllCategories() {
        doNothing().when(service).deleteAllCategories();
        ResponseEntity<Void> response = controller.deleteAllCategories();
        assertEquals(204, response.getStatusCodeValue());
    }
}

