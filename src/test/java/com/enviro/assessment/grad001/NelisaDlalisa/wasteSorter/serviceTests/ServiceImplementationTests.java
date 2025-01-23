package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.serviceTests;



import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dto.WasteCategoryDTO;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.entity.WasteCategory;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.exception.CategoryNotFoundException;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.repository.WasteCategoryRepository;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.service.ServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceImplementationTest {

    @Mock
    private WasteCategoryRepository wasteCategoryRepository;

    private ServiceImplementation service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new ServiceImplementation(wasteCategoryRepository);
    }

    @Test
    void testGetAllCategories() {
        WasteCategory wasteCategory1 = new WasteCategory(1L, "Metal", "Place in metal recycling bins.", "Remove any attached non-metal parts before recycling.");
        WasteCategory wasteCategory2 = new WasteCategory(2L, "Electronics", "Drop off at e-waste recycling centers.", "Do not throw electronics in regular bins; remove batteries where applicable.");
        List<WasteCategory> wasteCategories = Arrays.asList(wasteCategory1, wasteCategory2);
        when(wasteCategoryRepository.findAll()).thenReturn(wasteCategories);
        List<WasteCategoryDTO> result = service.getAllCategories();
        assertEquals(2, result.size());
        assertEquals("Metal", result.get(0).getName());
        assertEquals("Electronics", result.get(1).getName());
    }

    @Test
    void testGetCategoryById_Success() {
        WasteCategory wasteCategory = new WasteCategory(1L, "Electronics", "Drop off at e-waste recycling centers.", "Do not throw electronics in regular bins; remove batteries where applicable.");
        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.of(wasteCategory));
        WasteCategoryDTO result = service.getCategoryById(1L);
        assertEquals("Electronics", result.getName());
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CategoryNotFoundException.class, () -> service.getCategoryById(1L));
    }


    @Test
    void testUpdateCategory_Success() {
        WasteCategory existingCategory = new WasteCategory(1L, "Metal", "Place in metal recycling bins.", "Remove any attached non-metal parts before recycling.");
        WasteCategoryDTO updatedCategoryDTO = new WasteCategoryDTO(1L, "Metal", "Update disposal guidelines", "Update recycling tips");
        when(wasteCategoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(wasteCategoryRepository.save(existingCategory)).thenReturn(existingCategory);
        WasteCategoryDTO result = service.updateCategory(1L, updatedCategoryDTO);
        assertEquals("Update disposal guidelines", result.getDisposalGuidelines());
    }

    @Test
    void testDeleteCategory_Success() {
        when(wasteCategoryRepository.existsById(1L)).thenReturn(true);
        service.deleteCategory(1L);
        verify(wasteCategoryRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCategory_NotFound() {
        when(wasteCategoryRepository.existsById(1L)).thenReturn(false);
        assertThrows(CategoryNotFoundException.class, () -> service.deleteCategory(1L));
    }

    @Test
    void testDeleteAllCategories() {
        service.deleteAllCategories();
        verify(wasteCategoryRepository, times(1)).deleteAll();
    }
}
