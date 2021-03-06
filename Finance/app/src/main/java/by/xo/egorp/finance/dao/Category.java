package by.xo.egorp.finance.dao;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import by.xo.egorp.finance.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "CATEGORY".
 */
@Entity(active = true)
public class Category {

    @Id(autoincrement = true)
    private Long id;
    private Boolean categoryType;
    private String categoryName;
    private long categoryIconId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient CategoryDao myDao;

    @ToOne(joinProperty = "categoryIconId")
    private CategoryIcon categoryIcon;

    @Generated
    private transient Long categoryIcon__resolvedKey;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "categoryId")
    })
    private List<Subcategory> subcategories;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    @Generated
    public Category(Long id, Boolean categoryType, String categoryName, long categoryIconId) {
        this.id = id;
        this.categoryType = categoryType;
        this.categoryName = categoryName;
        this.categoryIconId = categoryIconId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCategoryDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Boolean categoryType) {
        this.categoryType = categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryIconId() {
        return categoryIconId;
    }

    public void setCategoryIconId(long categoryIconId) {
        this.categoryIconId = categoryIconId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public CategoryIcon getCategoryIcon() {
        long __key = this.categoryIconId;
        if (categoryIcon__resolvedKey == null || !categoryIcon__resolvedKey.equals(__key)) {
            __throwIfDetached();
            CategoryIconDao targetDao = daoSession.getCategoryIconDao();
            CategoryIcon categoryIconNew = targetDao.load(__key);
            synchronized (this) {
                categoryIcon = categoryIconNew;
            	categoryIcon__resolvedKey = __key;
            }
        }
        return categoryIcon;
    }

    @Generated
    public void setCategoryIcon(CategoryIcon categoryIcon) {
        if (categoryIcon == null) {
            throw new DaoException("To-one property 'categoryIconId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.categoryIcon = categoryIcon;
            categoryIconId = categoryIcon.getId();
            categoryIcon__resolvedKey = categoryIconId;
        }
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<Subcategory> getSubcategories() {
        if (subcategories == null) {
            __throwIfDetached();
            SubcategoryDao targetDao = daoSession.getSubcategoryDao();
            List<Subcategory> subcategoriesNew = targetDao._queryCategory_Subcategories(id);
            synchronized (this) {
                if(subcategories == null) {
                    subcategories = subcategoriesNew;
                }
            }
        }
        return subcategories;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetSubcategories() {
        subcategories = null;
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
