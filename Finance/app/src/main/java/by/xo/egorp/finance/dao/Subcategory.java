package by.xo.egorp.finance.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "SUBCATEGORY".
 */
@Entity
public class Subcategory {

    @Id(autoincrement = true)
    private Long id;
    private String subcategoryName;
    private long categoryId;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Subcategory() {
    }

    public Subcategory(Long id) {
        this.id = id;
    }

    @Generated
    public Subcategory(Long id, String subcategoryName, long categoryId) {
        this.id = id;
        this.subcategoryName = subcategoryName;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
