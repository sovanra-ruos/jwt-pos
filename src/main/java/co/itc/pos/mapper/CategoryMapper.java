package co.itc.pos.mapper;

import co.itc.pos.domain.Category;
import co.itc.pos.features.Category.dto.CategoryRequest;
import co.itc.pos.features.Category.dto.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //convert Category to CategoryResponse
    CategoryResponse toCategoryResponse(Category category);

    //convert CategoryRequest to Category
    Category toCategory(CategoryRequest categoryRequest);

}
