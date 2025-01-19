package co.itc.pos.features.Category;

import co.itc.pos.features.Category.dto.CategoryRequest;
import co.itc.pos.features.Category.dto.CategoryResponse;
import co.itc.pos.utils.CustomPage;

public interface CategoryService {

    CustomPage<CategoryResponse> findAll(int page, int size);
    CategoryResponse getCategoryByUuid(String uuid);
    CategoryResponse createCategory(CategoryRequest request);
    CategoryResponse updateCategory(String uuid, CategoryRequest request);
    void deleteCategory(String uuid);
    CategoryResponse disableCategory(String uuid);
    CategoryResponse enableCategory(String uuid);


}
