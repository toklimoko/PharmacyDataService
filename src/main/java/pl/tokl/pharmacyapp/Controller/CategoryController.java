package pl.tokl.pharmacyapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.tokl.pharmacyapp.Model.Category;

import pl.tokl.pharmacyapp.Model.Medicine;
import pl.tokl.pharmacyapp.Repository.CategoryRepository;
import pl.tokl.pharmacyapp.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/addcategory")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";

    }

    @GetMapping({"/allcategories"})
    public String allCategories(Model model) {
        List<Category> allCategories = this.categoryRepository.findAll();
        model.addAttribute("categories", allCategories);
        return "allcategories";
    }

    @GetMapping("/category/{id}")
    public String getCategory(Model model, @PathVariable Long id){
        Optional<Category> categoryById = categoryRepository.findById(id);
        categoryById.ifPresent(category -> model.addAttribute("category",category));
        return (String)categoryById.map(category -> "singleCategory").orElse("error");
    }

    @GetMapping("/category/edit/{id}")
//    public String edit(@PathVariable Long id, @PathVariable("name") String name, @PathVariable("substance") String substance, @PathVariable("dose") String dose, @PathVariable("units") int units, @PathVariable("packages") int packages){
    public String edit(Model model,@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            model.addAttribute("id", id);
        }
        return "editcategory";
    }

    @PostMapping("/category/updateEdited/{id}")
    public String saveEditedCategory(Category category,@PathVariable Long id){
        categoryService.update(id, category);
        return "redirect:/allcategories";
    }

    @PostMapping("/category/save")
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/allcategories";
    }

    @GetMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryRepository.deleteById(id);
        return "redirect:/allcategories";
    }

    @PostMapping("/category/search")
    public String search(Model model, @RequestParam("word") String word, @RequestParam String query) {
//        List<Medicine> medicineSearch = medicineService.search(word);

        List<Category> foundCategories = new ArrayList<>();

        switch(query){
            case "name":
                foundCategories = categoryRepository.findAllByNameContaining(word);
                break;
            case "description":
                foundCategories = categoryRepository.findAllByDescriptionContaining(word);
                break;
        }

        if (!foundCategories.isEmpty()){
            model.addAttribute("categorySearch",foundCategories);
        }

        return "resultscategory";
    }



    @GetMapping("/category/name/asc")
    public String sortByNameAsc(Model model){
        List<Category> categories = categoryRepository.findByOrderByNameAsc();
        model.addAttribute("categories",categories);
        return "allcategories";
    }

    @GetMapping("/category/name/desc")
    public String sortByNameDesc(Model model){
        List<Category> categories = categoryRepository.findByOrderByNameDesc();
        model.addAttribute("categories",categories);
        return "allcategories";
    }

    @GetMapping("/category/description/asc")
    public String sortByDescriptionAsc(Model model){
        List<Category> categories = categoryRepository.findByOrderByDescriptionAsc();
        model.addAttribute("categories",categories);
        return "allcategories";
    }

    @GetMapping("/category/description/desc")
    public String sortByDescriptionDesc(Model model){
        List<Category> categories = categoryRepository.findByOrderByDescriptionDesc();
        model.addAttribute("categories",categories);
        return "allcategories";
    }


}
