package by.xo.egorp.finance;


import android.app.Application;

import org.greenrobot.greendao.database.Database;

import by.xo.egorp.finance.bal.ManagementOfCategories;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.DaoMaster;
import by.xo.egorp.finance.dao.DaoSession;

public class AppController extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "XO-finance-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        fillingWithBaseValues();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    public void fillingWithBaseValues() {

        ManagementOfWallets managementOfWallets = new ManagementOfWallets();
        ManagementOfCategories managementOfCategories = new ManagementOfCategories();

        if (managementOfWallets.getAllWalletIcons().size() == 0) {
            managementOfWallets.addWalletIcon(R.drawable.ic_menu_camera);
            managementOfWallets.addWalletIcon(R.drawable.ic_menu_gallery);
            managementOfWallets.addWalletIcon(R.drawable.ic_menu_slideshow);
        }

        if (managementOfWallets.getAllCurrencies().size() == 0) {
            managementOfWallets.addCurrency(getStringResourceByName("name_byn"), "BYN");
            managementOfWallets.addCurrency(getStringResourceByName("name_eur"), "EUR");
            managementOfWallets.addCurrency(getStringResourceByName("name_usd"), "USD");
            managementOfWallets.addCurrency(getStringResourceByName("name_rub"), "RUB");
            managementOfWallets.addCurrency(getStringResourceByName("name_gbp"), "GBP");
            managementOfWallets.addCurrency(getStringResourceByName("name_nzd"), "NZD");
            managementOfWallets.addCurrency(getStringResourceByName("name_cad"), "CAD");
            managementOfWallets.addCurrency(getStringResourceByName("name_chf"), "CHF");
            managementOfWallets.addCurrency(getStringResourceByName("name_jpy"), "JPY");
            managementOfWallets.addCurrency(getStringResourceByName("name_cny"), "CNY");
        }


        if (managementOfCategories.getAllCategories().size() == 0 &&
                managementOfCategories.getAllSubcategories().size() == 0 &&
                managementOfCategories.getAllCategoryIcons().size() == 0) {

            boolean expenses = false;
            boolean income = true;

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_share);
            managementOfCategories.addCategory(false, getStringResourceByName("name_auto"),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    getResources().getStringArray(R.array.auto)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_camera);
            managementOfCategories.addCategory(false, getStringResourceByName("name_bank"),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    getResources().getStringArray(R.array.bank)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_send);
            managementOfCategories.addCategory(false, getStringResourceByName("name_house"),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    getResources().getStringArray(R.array.house)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_send);
            managementOfCategories.addCategory(false, getStringResourceByName("name_pets"),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    getResources().getStringArray(R.array.pets)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

        }

    }
}
