package by.xo.egorp.finance.dao;

import org.greenrobot.greendao.annotation.*;

import by.xo.egorp.finance.dao.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "WALLET".
 */
@Entity(active = true)
public class Wallet {

    @Id(autoincrement = true)
    private Long id;
    private String walletName;
    private Float balance;
    private Boolean mainWallet;
    private Integer background;
    private long currencyId;
    private long walletIconId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient WalletDao myDao;

    @ToOne(joinProperty = "currencyId")
    private Currency currency;

    @Generated
    private transient Long currency__resolvedKey;

    @ToOne(joinProperty = "walletIconId")
    private WalletIcon walletIcon;

    @Generated
    private transient Long walletIcon__resolvedKey;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Wallet() {
    }

    public Wallet(Long id) {
        this.id = id;
    }

    @Generated
    public Wallet(Long id, String walletName, Float balance, Boolean mainWallet, Integer background, long currencyId, long walletIconId) {
        this.id = id;
        this.walletName = walletName;
        this.balance = balance;
        this.mainWallet = mainWallet;
        this.background = background;
        this.currencyId = currencyId;
        this.walletIconId = walletIconId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWalletDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Boolean getMainWallet() {
        return mainWallet;
    }

    public void setMainWallet(Boolean mainWallet) {
        this.mainWallet = mainWallet;
    }

    public Integer getBackground() {
        return background;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    public long getWalletIconId() {
        return walletIconId;
    }

    public void setWalletIconId(long walletIconId) {
        this.walletIconId = walletIconId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public Currency getCurrency() {
        long __key = this.currencyId;
        if (currency__resolvedKey == null || !currency__resolvedKey.equals(__key)) {
            __throwIfDetached();
            CurrencyDao targetDao = daoSession.getCurrencyDao();
            Currency currencyNew = targetDao.load(__key);
            synchronized (this) {
                currency = currencyNew;
            	currency__resolvedKey = __key;
            }
        }
        return currency;
    }

    @Generated
    public void setCurrency(Currency currency) {
        if (currency == null) {
            throw new DaoException("To-one property 'currencyId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.currency = currency;
            currencyId = currency.getId();
            currency__resolvedKey = currencyId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public WalletIcon getWalletIcon() {
        long __key = this.walletIconId;
        if (walletIcon__resolvedKey == null || !walletIcon__resolvedKey.equals(__key)) {
            __throwIfDetached();
            WalletIconDao targetDao = daoSession.getWalletIconDao();
            WalletIcon walletIconNew = targetDao.load(__key);
            synchronized (this) {
                walletIcon = walletIconNew;
            	walletIcon__resolvedKey = __key;
            }
        }
        return walletIcon;
    }

    @Generated
    public void setWalletIcon(WalletIcon walletIcon) {
        if (walletIcon == null) {
            throw new DaoException("To-one property 'walletIconId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.walletIcon = walletIcon;
            walletIconId = walletIcon.getId();
            walletIcon__resolvedKey = walletIconId;
        }
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
