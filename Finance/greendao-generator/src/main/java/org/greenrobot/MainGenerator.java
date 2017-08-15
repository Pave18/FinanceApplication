package org.greenrobot;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;

public class MainGenerator {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "by.xo.egorp.finance.dao");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema, "./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Entity walletIcon;
    private static Entity currency;

    private static Entity wallet;
    //------------------------------------//
    private static Entity categoryIcon;
    private static Entity subcategory;
    private static Property categoryIdToSubcategory;

    private static Entity category;

    private static void addTables(final Schema schema) {
        addWalletIconEntities(schema);
        addCurrencyTypeEntities(schema);

        addWalletEntities(schema);
        //------------------------------------//
        addCategoryIconEntities(schema);
        addSubcategoryEntities(schema);

        addCategoryEntities(schema);
        //------------------------------------//
        addTransactionEntities(schema);
    }

    private static Entity addWalletIconEntities(final Schema schema) {
        walletIcon = schema.addEntity("WalletIcon");
        walletIcon.addIdProperty().primaryKey().autoincrement();
        walletIcon.addStringProperty("walletPic");
        return walletIcon;
    }

    private static Entity addCurrencyTypeEntities(final Schema schema) {
        currency = schema.addEntity("Currency");
        currency.addIdProperty().primaryKey().autoincrement();
        currency.addStringProperty("currencyName");
        currency.addStringProperty("currencyCode");
        return currency;
    }

    private static Entity addWalletEntities(final Schema schema) {
        wallet = schema.addEntity("Wallet");
        wallet.addIdProperty().primaryKey().autoincrement();
        wallet.addStringProperty("walletName");
        wallet.addFloatProperty("balance");

        Property currencyId = wallet.addLongProperty("currencyId").notNull().getProperty();
        wallet.addToOne(currency, currencyId).setName("currency");
        Property walletIconId = wallet.addLongProperty("walletIconId").notNull().getProperty();
        wallet.addToOne(walletIcon, walletIconId).setName("walletIcon");
        return wallet;
    }


    private static Entity addCategoryIconEntities(final Schema schema) {
        categoryIcon = schema.addEntity("CategoryIcon");
        categoryIcon.addIdProperty().primaryKey().autoincrement();
        categoryIcon.addStringProperty("categoryPic");
        return categoryIcon;
    }

    private static Entity addSubcategoryEntities(final Schema schema) {
        subcategory = schema.addEntity("Subcategory");
        subcategory.addIdProperty().primaryKey().autoincrement();
        subcategory.addStringProperty("subcategoryName");

        categoryIdToSubcategory = subcategory.addLongProperty("categoryId").notNull().getProperty();
        return subcategory;
    }

    private static Entity addCategoryEntities(final Schema schema) {
        category = schema.addEntity("Category");
        category.addIdProperty().primaryKey().autoincrement();
        category.addBooleanProperty("categoryType");
        category.addStringProperty("categoryName");

        Property categoryIconId = category.addLongProperty("categoryIconId").notNull().getProperty();
        category.addToOne(categoryIcon, categoryIconId).setName("categoryIcon");

        category.addToMany(subcategory, categoryIdToSubcategory).setName("subcategories");
        return category;
    }


    private static Entity addTransactionEntities(final Schema schema) {
        Entity transaction = schema.addEntity("FinanceTransaction");
        transaction.addIdProperty().primaryKey().autoincrement();
        transaction.addBooleanProperty("transactionType");
        transaction.addFloatProperty("amount");
        transaction.addStringProperty("data");
        transaction.addStringProperty("description");
        transaction.addByteProperty("photo");

        Property walletId = transaction.addLongProperty("walletId").notNull().getProperty();
        transaction.addToOne(wallet, walletId).setName("wallet");
        Property categoryId = transaction.addLongProperty("categoryId").notNull().getProperty();
        transaction.addToOne(category, categoryId).setName("category");
        return transaction;
    }
}
