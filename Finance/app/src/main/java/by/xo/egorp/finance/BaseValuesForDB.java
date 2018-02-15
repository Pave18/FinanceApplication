package by.xo.egorp.finance;

import android.content.Context;

import by.xo.egorp.finance.bal.ManagementOfCategories;
import by.xo.egorp.finance.bal.ManagementOfWallets;

public class BaseValuesForDB {

    public BaseValuesForDB(Context context) {
        fillingWithBaseValues(context);
    }

    private void fillingWithBaseValues(Context context) {

        ManagementOfWallets managementOfWallets = new ManagementOfWallets();
        ManagementOfCategories managementOfCategories = new ManagementOfCategories();

        if (managementOfWallets.getAllWalletIcons().size() == 0) {
            managementOfWallets.addWalletIcon(R.drawable.ic_work_black_24dp);
            managementOfWallets.addWalletIcon(R.drawable.ic_menu_gallery);
            managementOfWallets.addWalletIcon(R.drawable.ic_menu_slideshow);
        }

        if (managementOfWallets.getAllCurrencies().size() == 0) {
            managementOfWallets.addCurrency(context.getString(R.string.name_byn), "BYN");
            managementOfWallets.addCurrency(context.getString(R.string.name_eur), "EUR");
            managementOfWallets.addCurrency(context.getString(R.string.name_usd), "USD");
            managementOfWallets.addCurrency(context.getString(R.string.name_rub), "RUB");
            managementOfWallets.addCurrency(context.getString(R.string.name_gbp), "GBP");
            managementOfWallets.addCurrency(context.getString(R.string.name_nzd), "NZD");
            managementOfWallets.addCurrency(context.getString(R.string.name_cad), "CAD");
            managementOfWallets.addCurrency(context.getString(R.string.name_chf), "CHF");
            managementOfWallets.addCurrency(context.getString(R.string.name_jpy), "JPY");
            managementOfWallets.addCurrency(context.getString(R.string.name_cny), "CNY");
        }


        if (managementOfCategories.getAllCategories().size() == 0 &&
                managementOfCategories.getAllSubcategories().size() == 0 &&
                managementOfCategories.getAllCategoryIcons().size() == 0) {

            boolean expenses = false;
            boolean income = true;

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_share);
            managementOfCategories.addCategory(false, context.getString(R.string.name_auto),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    context.getResources().getStringArray(R.array.auto)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_camera);
            managementOfCategories.addCategory(false, context.getString(R.string.name_bank),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    context.getResources().getStringArray(R.array.bank)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_send);
            managementOfCategories.addCategory(false, context.getString(R.string.name_house),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    context.getResources().getStringArray(R.array.house)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

            managementOfCategories.addCategoryIcon(R.drawable.ic_menu_send);
            managementOfCategories.addCategory(false, context.getString(R.string.name_pets),
                    managementOfCategories.getLastAddedCategoryIcon());
            for (String s :
                    context.getResources().getStringArray(R.array.pets)) {
                managementOfCategories.addSubcategory(s, managementOfCategories.getLastAddedCategoryId());
            }

        }

    }
}
