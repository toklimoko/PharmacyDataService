package pl.tokl.pharmacyapp.service;

import org.springframework.stereotype.Service;
import pl.tokl.pharmacyapp.Model.Medicine;
import pl.tokl.pharmacyapp.Repository.MedicineRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {


    private MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Transactional
    public void update(Long id, Medicine medicine){
        Optional<Medicine> byId = medicineRepository.findById(id);
        Medicine tempMedicine = byId.get();
        tempMedicine.setName(medicine.getName());
        tempMedicine.setSubstance(medicine.getSubstance());
        tempMedicine.setCategory(medicine.getCategory());
        tempMedicine.setDose(medicine.getDose());
        tempMedicine.setUnits(medicine.getUnits());
        tempMedicine.setPackages(medicine.getPackages());
    }

//    public List<Medicine> search(String word) {
//        List<Medicine> byName = medicineRepository.findAllByNameContaining(word);
//        List<Medicine> bySubstance = medicineRepository.findAllBySubstanceContaining(word);
////        List<Medicine> byCategory = medicineRepository.findAllByCategoryContaining(word);
////        List<Medicine> byDose = medicineRepository.findAllByDoseContaining(word);
////        List<Medicine> byUnits = medicineRepository.findAllByUnitsContaining(word);
////        List<Medicine> byPackages = medicineRepository.findAllByPackagesContaining(word);
//        List<Medicine> allFound = new ArrayList<>();
//        allFound.addAll(byName);
//        allFound.addAll(bySubstance);
////        allFound.addAll(byCategory);
////        allFound.addAll(byDose);
////        allFound.addAll(byUnits);
////        allFound.addAll(byPackages);
//
//        return allFound;
//    }
}
