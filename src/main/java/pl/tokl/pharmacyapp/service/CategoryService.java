package pl.tokl.pharmacyapp.service;

import org.springframework.stereotype.Service;
import pl.tokl.pharmacyapp.Model.Category;
import pl.tokl.pharmacyapp.Model.Medicine;
import pl.tokl.pharmacyapp.Repository.CategoryRepository;
import pl.tokl.pharmacyapp.Repository.MedicineRepository;

import javax.transaction.Transactional;

import java.util.Optional;

@Service
public class CategoryService {


    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void update(Long id, Category category){
        Optional<Category> byId = categoryRepository.findById(id);
        Category tempCategory = byId.get();
        tempCategory.setName(category.getName());
        tempCategory.setDescription(category.getDescription());
    }

}
