package module4.projectmd4.service;

import module4.projectmd4.model.dto.request.FormCategory;
import module4.projectmd4.model.entity.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Page<Category> findCategoriesByKeyword(String keyword, int page, int size, String sortBy, String sortDirection);

    void add(FormCategory formCategory);
}
