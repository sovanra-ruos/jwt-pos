package co.itc.pos.features.Category;

import co.itc.pos.domain.Category;
import co.itc.pos.features.Category.dto.CategoryRequest;
import co.itc.pos.features.Category.dto.CategoryResponse;
import co.itc.pos.mapper.CategoryMapper;
import co.itc.pos.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CustomPage<CategoryResponse> findAll(int page, int size) {

        Page<Category> categories = categoryRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));

        return customPage(categories.map(categoryMapper::toCategoryResponse));
    }

    @Override
    public CategoryResponse getCategoryByUuid(String uuid) {

        Category category = categoryRepository.findByUuid(uuid);

        if(category == null){
            throw new NoSuchElementException("Category with uuid: " + uuid + " not found");
        }


        return categoryMapper.toCategoryResponse(category);
    }



    @Override
    public CategoryResponse createCategory(CategoryRequest request) {

        Category category = categoryMapper.toCategory(request);

        category.setUuid(UUID.randomUUID().toString());

        categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(String uuid, CategoryRequest request) {

        Category category = categoryRepository.findByUuid(uuid);

        if (category == null) {
            throw new NoSuchElementException("Category with uuid: " + uuid + " not found");
        }

        category.setName(request.name());

        categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public void deleteCategory(String uuid) {

        Category category = categoryRepository.findByUuid(uuid);

        if (category == null) {
            throw new NoSuchElementException("Category with uuid: " + uuid + " not found");
        }

        categoryRepository.delete(category);

    }

    @Override
    public CategoryResponse disableCategory(String uuid) {

        Category category = categoryRepository.findByUuid(uuid);

        if (category == null) {
            throw new NoSuchElementException("Category with uuid: " + uuid + " not found");
        }

        category.setActive(false);

        categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse enableCategory(String uuid) {

        Category category = categoryRepository.findByUuid(uuid);

        if (category == null) {
            throw new NoSuchElementException("Category with uuid: " + uuid + " not found");
        }

        category.setActive(true);

        categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(category);
    }

    public CustomPage<CategoryResponse> customPage(Page<CategoryResponse> page){

        CustomPage<CategoryResponse> customPage = new CustomPage<>();

        //check if page has next
        if(page != null && page.hasPrevious()){
            customPage.setPrevious(true); // Set to true if there is a previous page
        } else {
            customPage.setPrevious(false); // Set to false if there is no previous page
        }

        if(page != null && page.hasNext()){
            customPage.setNext(true); // Set to true if there is a next page
        } else {
            customPage.setNext(false); // Set to false if there is no next page
        }

        //set total
        customPage.setTotal((int) page.getTotalElements());
        customPage.setTotalElements(page.getTotalElements());

        //set total pages
        customPage.setResults(page.getContent());

        return customPage;
    }
}
