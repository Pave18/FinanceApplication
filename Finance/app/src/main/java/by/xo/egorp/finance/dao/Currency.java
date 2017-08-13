package by.xo.egorp.finance.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "CURRENCY".
 */
@Entity
public class Currency {

    @Id(autoincrement = true)
    private Long id;
    private String currencyName;
    private String currencyCode;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Currency() {
    }

    public Currency(Long id) {
        this.id = id;
    }

    @Generated
    public Currency(Long id, String currencyName, String currencyCode) {
        this.id = id;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}