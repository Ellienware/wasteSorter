package com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.dataInitializer;


import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.entity.WasteCategory;
import com.enviro.assessment.grad001.NelisaDlalisa.wasteSorter.repository.WasteCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WasteCategoryRepository wasteCategoryRepository;

    public DataInitializer(WasteCategoryRepository wasteCategoryRepository) {
        this.wasteCategoryRepository = wasteCategoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if the table is empty and insert data if so
        if (wasteCategoryRepository.count() == 0) {
            WasteCategory metal = new WasteCategory(
                    null,
                    "Metal",
                    "Place in metal recycling bins.",
                    "Remove any attached non-metal parts before recycling.");
            WasteCategory Electronics = new WasteCategory(
                    null,
                    "Electronics",
                    "Drop off at e-waste recycling centers.",
                    "Do not throw electronics in regular bins; remove batteries where applicable.");
            WasteCategory organicWaste = new WasteCategory(
                    null,
                    "Organic Waste",
                    "Place in compostable waste bins.",
                    "Avoid including meat or dairy products in compost.");

            wasteCategoryRepository.save(metal);
            wasteCategoryRepository.save(Electronics);
            wasteCategoryRepository.save(organicWaste);
        }
    }
}

