package fr.mns.locmns.service.material.impl;

import fr.mns.locmns.domain.model.material.Category;
import fr.mns.locmns.domain.repository.material.CategoryRepository;
import fr.mns.locmns.dto.material.category.CategoryCreateRequest;
import fr.mns.locmns.dto.material.category.CategoryDetails;
import fr.mns.locmns.dto.material.category.CategoryUpdateRequest;
import fr.mns.locmns.service.material.CategoryService;
import fr.mns.locmns.tools.exception.BadRequestException;
import fr.mns.locmns.tools.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDetails getDetails(Integer id) {
        Category category = categoryRepository.findByIdNullSafe(id);
        if (category == null) {
            throw new NotFoundException();
        }
        return getDetails(category);
    }

    @Override
    public List<CategoryDetails> getList() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDetails> categoryDetailsList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDetails details = getDetails(category);
            categoryDetailsList.add(details);
        }
        return categoryDetailsList;
    }

    @Override
    public CategoryDetails create(CategoryCreateRequest request) {
        if (!StringUtils.hasText(request.getName())) {
            throw new BadRequestException("Name should not be empty");
        }
        Category category = new Category();
        category.setName(request.getName());
        category = categoryRepository.save(category);
        return getDetails(category);
    }

    @Override
    public CategoryDetails update(Integer id, CategoryUpdateRequest request) {
        Category category = categoryRepository.findByIdNullSafe(id);
        if (category == null) {
            throw new NotFoundException();
        }
        if (!StringUtils.hasText(request.getName())) {
            throw new BadRequestException("Name should not be empty");
        }
        category.setName(request.getName());
        category = categoryRepository.save(category);
        return getDetails(category);
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryRepository.findByIdNullSafe(id);
        if (category == null) {
            throw new NotFoundException();
        }
        categoryRepository.delete(category);
    }

    private CategoryDetails getDetails(Category category) {
        CategoryDetails details = new CategoryDetails();
        details.setId(category.getId());
        details.setName(category.getName());
        return details;
    }
}
