package com.OnlineLibrary.Seed;

import com.OnlineLibrary.Entities.sql.Category;
import com.OnlineLibrary.Repository.sql.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class SQLCategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public SQLCategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.asList(
                    new Category("Fiction"),
                    new Category("Science"),
                    new Category("History"),
                    new Category("Mystery & Thriller"),
                    new Category("Technology & Computing"),
                    new Category("Biographies & Memoirs"),
                    new Category("Fantasy"),
                    new Category("Self-Help & Motivation"),
                    new Category("Romance"),
                    new Category("Science Fiction"),
                    new Category("Business & Finance"),
                    new Category("Travel Guides"),
                    new Category("Poetry")
            );
            categoryRepository.saveAll(categories);
        }
    }
}