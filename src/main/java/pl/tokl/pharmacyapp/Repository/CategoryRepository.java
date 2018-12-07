package pl.tokl.pharmacyapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tokl.pharmacyapp.Model.Category;
import pl.tokl.pharmacyapp.Model.Medicine;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameIgnoreCase(String name);
    List<Category> findByDescriptionIgnoreCase(String substance);

    List<Category> findAllByNameContaining(String word);
    List<Category> findAllByDescriptionContaining(String word);

    List<Category> findByOrderByNameAsc();
    List<Category> findByOrderByNameDesc();
    List<Category> findByOrderByDescriptionAsc();
    List<Category> findByOrderByDescriptionDesc();
}
