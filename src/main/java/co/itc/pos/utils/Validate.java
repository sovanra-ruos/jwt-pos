package co.itc.pos.utils;

import co.itc.pos.features.Category.CategoryRepository;
import co.itc.pos.features.Product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validate {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;


    public void validateCategory(String name) {
        if(categoryRepository.existsByName(name)){
            throw new IllegalArgumentException("Category with name: " + name + " already exist");
        }
    }

    public void validateProduct(String name){
        if(productRepository.existsByName(name)){
            throw new IllegalArgumentException("Product with name: " + name + " already exist");
        }
    }



}
