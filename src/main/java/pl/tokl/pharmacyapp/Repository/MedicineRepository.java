package pl.tokl.pharmacyapp.Repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.tokl.pharmacyapp.Model.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    Optional<Medicine> findByNameIgnoreCase(String name);
    List<Medicine> findBySubstanceIgnoreCase(String substance);
    List<Medicine> findByCategoryNameIgnoreCase(String category);

    List<Medicine> findAllByNameContaining(String word);
    List<Medicine> findAllBySubstanceContaining(String word);
    List<Medicine> findAllByCategoryNameContaining(String word);
    List<Medicine> findAllByDoseContaining(String word);
    List<Medicine> findAllByUnitsContaining(String word);
    List<Medicine> findAllByPackagesContaining(String word);

    List<Medicine> findByOrderByNameAsc();
    List<Medicine> findByOrderByNameDesc();
    List<Medicine> findByOrderBySubstanceAsc();
    List<Medicine> findByOrderBySubstanceDesc();
    List<Medicine> findByOrderByCategoryNameAsc();
    List<Medicine> findByOrderByCategoryNameDesc();
    List<Medicine> findByOrderByDoseAsc();
    List<Medicine> findByOrderByDoseDesc();
    List<Medicine> findByOrderByUnitsAsc();
    List<Medicine> findByOrderByUnitsDesc();
    List<Medicine> findByOrderByPackagesAsc();
    List<Medicine> findByOrderByPackagesDesc();





}
