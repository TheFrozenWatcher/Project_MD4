package module4.projectmd4.service.impl;

import lombok.RequiredArgsConstructor;
import module4.projectmd4.model.dto.request.FormCategory;
import module4.projectmd4.model.entity.Category;
import module4.projectmd4.repository.ICategoryRepository;
import module4.projectmd4.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    public Page<Category> findCategoriesByKeyword(String keyword, int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return categoryRepository.findByKeyword(keyword, pageable);
    }

    @Override
    public void add(FormCategory formCategory) {
        Category category=Category.builder().categoryName(formCategory.getCategoryName())
                .description(formCategory.getDescription())
                .status(true)
                .build();
        categoryRepository.save(category);
    }
}
