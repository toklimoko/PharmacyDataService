package pl.tokl.pharmacyapp.Model;

import javax.persistence.*;

@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Column
    private String substance;
    @ManyToOne
    private Category category;
    @Column
    private String dose;
    @Column
    private int units;
    @Column
    private int packages;

    public Medicine(String name, String substance, Category category, String dose, int units, int packages) {
        this.name = name;
        this.substance = substance;
        this.category = category;
        this.dose = dose;
        this.units = units;
        this.packages = packages;
    }

    public Medicine(String name, String substance, String dose, int units, int packages) {
        this.name = name;
        this.substance = substance;
        this.dose = dose;
        this.units = units;
        this.packages = packages;
    }

    public Medicine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }
}
