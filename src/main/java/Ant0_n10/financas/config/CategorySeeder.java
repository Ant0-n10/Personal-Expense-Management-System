package Ant0_n10.financas.config;

import Ant0_n10.financas.models.Category;
import Ant0_n10.financas.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0){
            System.out.println("Empty database. Initializing default categories...");

            Category food = new Category("food");
            Category transport = new Category("transport");
            Category leisure = new Category("leisure");
            Category health = new Category("health");

            categoryRepository.saveAll(List.of(food,transport,leisure,health));

            System.out.println("Standard categories successfully registered!");
        }
    }
}
