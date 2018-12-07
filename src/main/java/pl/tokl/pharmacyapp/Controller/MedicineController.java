package pl.tokl.pharmacyapp.Controller;

import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.tokl.pharmacyapp.Model.Medicine;
import pl.tokl.pharmacyapp.Repository.CategoryRepository;
import pl.tokl.pharmacyapp.Repository.MedicineRepository;
import pl.tokl.pharmacyapp.service.MedicineService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MedicineController {

    private MedicineRepository medicineRepository;
    private CategoryRepository categoryRepository;
    private MedicineService medicineService;


    @Autowired
    public MedicineController(MedicineRepository medicineRepository, MedicineService medicineService, CategoryRepository categoryRepository) {
        this.medicineRepository = medicineRepository;
        this.medicineService = medicineService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    //DELETE IF NEEDED
    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("medicine", new Medicine());
        model.addAttribute("categories",categoryRepository.findAll());
        return "add";
    }

    @PostMapping({"/save"})
    public String addMedicine(Medicine medicine) {
        this.medicineRepository.save(medicine);
        return "redirect:/allmedicines";
    }

    @GetMapping({"/allmedicines"})
    public String allMedicines(Model model) {
        List<Medicine> allMedicines = this.medicineRepository.findAll();
        model.addAttribute("medicines", allMedicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/{id}")
    public String getMedicine(Model model, @PathVariable Long id){
        Optional<Medicine> medicineById = medicineRepository.findById(id);
        medicineById.ifPresent(medicine -> model.addAttribute("medicine",medicine));

        return (String)medicineById.map(medicine -> "singleMedicine").orElse("error");
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id){
        medicineRepository.deleteById(id);
        return "redirect:/allmedicines";
    }

    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable Long id, @PathVariable("name") String name, @PathVariable("substance") String substance, @PathVariable("dose") String dose, @PathVariable("units") int units, @PathVariable("packages") int packages){
    public String edit(Model model,@PathVariable Long id){
//        addSubmit(new Medicine(id));
        Optional<Medicine> medicine = medicineRepository.findById(id);
        if(medicine.isPresent()){
            model.addAttribute("medicine", medicine.get());
            model.addAttribute("id", id);
        }
        return "edit";
    }

    @PostMapping("/updateEdited/{id}")
    public String saveEditedMedicine(Medicine medicine,@PathVariable Long id){
        medicineService.update(id, medicine);
        return "redirect:/allmedicines";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam("word") String word, @RequestParam String query) {
//        List<Medicine> medicineSearch = medicineService.search(word);

        List<Medicine> foundMedicines = new ArrayList<>();

        switch(query){
            case "name":
                foundMedicines = medicineRepository.findAllByNameContaining(word);
                break;
            case "substance":
                foundMedicines = medicineRepository.findAllBySubstanceContaining(word);
                break;
            case "category":
                foundMedicines = medicineRepository.findAllByCategoryNameContaining(word);
                break;
        }

        if (!foundMedicines.isEmpty()){
            model.addAttribute("medicineSearch",foundMedicines);
        }

        return "results";
    }

    @GetMapping("/medicine/name/asc")
    public String sortByNameAsc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByNameAsc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/name/desc")
    public String sortByNameDesc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByNameDesc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/substance/asc")
    public String sortBySubstanceAsc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderBySubstanceAsc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/substance/desc")
    public String sortBySubstanceDesc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderBySubstanceDesc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/category/asc")
    public String sortByCategoryAsc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByCategoryNameAsc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/category/desc")
    public String sortByCategoryDesc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByCategoryNameDesc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/dose/asc")
    public String sortByDoseAsc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByDoseAsc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/dose/desc")
    public String sortByDoseDesc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByDoseDesc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/units/asc")
    public String sortByUnitsAsc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByUnitsAsc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/units/desc")
    public String sortByUnitsDesc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByUnitsDesc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/packages/asc")
    public String sortByPackagesAsc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByPackagesAsc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }

    @GetMapping("/medicine/packages/desc")
    public String sortByPackagesDesc(Model model){
        List<Medicine> medicines = medicineRepository.findByOrderByPackagesDesc();
        model.addAttribute("medicines",medicines);
        return "allmedicines";
    }


}
