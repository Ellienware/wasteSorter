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
            WasteCategory plastic = new WasteCategory(
                    null,
                    "Plastic",
                    "Place in recycling bins for plastics.",
                    "Wash and dry before recycling.");
            WasteCategory glass = new WasteCategory(
                    null,
                    "Glass",
                    "Dispose of in glass bins.",
                    "Avoid breaking glass during disposal.");
            WasteCategory paper = new WasteCategory(
                    null,
                    "Paper",
                    "Dispose in paper recycling bins.",
                    "Keep dry to avoid contamination.");

            wasteCategoryRepository.save(plastic);
            wasteCategoryRepository.save(glass);
            wasteCategoryRepository.save(paper);
        }
    }
}

