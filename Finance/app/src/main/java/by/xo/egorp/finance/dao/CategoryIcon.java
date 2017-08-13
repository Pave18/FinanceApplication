package by.xo.egorp.finance.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "CATEGORY_ICON".
 */
@Entity
public class CategoryIcon {

    @Id(autoincrement = true)
    private Long id;
    private String categoryPic;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public CategoryIcon() {
    }

    public CategoryIcon(Long id) {
        this.id = id;
    }

    @Generated
    public CategoryIcon(Long id, String categoryPic) {
        this.id = id;
        this.categoryPic = categoryPic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
