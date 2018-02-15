package by.xo.egorp.finance.bal;

import java.util.List;

import by.xo.egorp.finance.AppController;
import by.xo.egorp.finance.dao.Category;
import by.xo.egorp.finance.dao.CategoryDao;
import by.xo.egorp.finance.dao.CategoryIcon;
import by.xo.egorp.finance.dao.CategoryIconDao;
import by.xo.egorp.finance.dao.DaoSession;
import by.xo.egorp.finance.dao.Subcategory;
import by.xo.egorp.finance.dao.SubcategoryDao;

public class ManagementOfCategories {

    private DaoSession daoSession;
    private CategoryIconDao categoryIconDao;
    private CategoryDao categoryDao;
    private SubcategoryDao subcategoryDao;

    private List<CategoryIcon> categoryIcons;
    private List<Category> categories;
    private List<Subcategory> subcategories;

    public ManagementOfCategories() {
        this.daoSession = AppController.getDaoSession();

        categoryIconDao = this.daoSession.getCategoryIconDao();
        categoryDao = this.daoSession.getCategoryDao();
        subcategoryDao = this.daoSession.getSubcategoryDao();

        getAllCategoryIcons();
        getAllCategories();
        getAllSubcategories();
    }

    public void addCategoryIcon(int categoryPic) {
        CategoryIcon tempCategoryIcon = new CategoryIcon();
        tempCategoryIcon.setCategoryPic(categoryPic);
        categoryIconDao.insert(tempCategoryIcon);
    }

    public List<CategoryIcon> getAllCategoryIcons() {
        categoryIcons = categoryIconDao.loadAll();
        return categoryIcons;
    }

    public CategoryIcon getLastAddedCategoryIcon() {
        List<CategoryIcon> tempCategoryIconList = getAllCategoryIcons();
        return tempCategoryIconList.get(tempCategoryIconList.size() - 1);
    }

    public void addCategory(boolean type, String categoryName, CategoryIcon categoryIcon) {
        Category tempCategory = new Category();
        tempCategory.setCategoryType(type);
        tempCategory.setCategoryName(categoryName);
        tempCategory.setCategoryIcon(categoryIcon);
        tempCategory.setCategoryIcon(categoryIcon);
        categoryDao.insert(tempCategory);
    }

    public void addCategory(long id, boolean type, String categoryName, CategoryIcon categoryIcon) {
        Category tempCategory = new Category();
        tempCategory.setCategoryType(type);
        tempCategory.setCategoryName(categoryName);
        tempCategory.setCategoryIcon(categoryIcon);
        categoryDao.insert(tempCategory);
    }


    public List<Category> getAllCategories() {
        categories = categoryDao.loadAll();
        return categories;
    }

    public long getLastAddedCategoryId() {
        List<Category> lastAddedCategory = getAllCategories();
        return lastAddedCategory.get(lastAddedCategory.size() - 1).getId();
    }

    public void addSubcategory(String subcategoryName, long categoryId) {
        Subcategory tempSubcategory = new Subcategory();
        tempSubcategory.setSubcategoryName(subcategoryName);
        tempSubcategory.setCategoryId(categoryId);
        subcategoryDao.insert(tempSubcategory);
    }


    public List<Subcategory> getAllSubcategories() {
        subcategories = subcategoryDao.loadAll();
        return subcategories;
    }


}
