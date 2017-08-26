package by.xo.egorp.finance.bal;

import java.util.List;

import by.xo.egorp.finance.AppController;
import by.xo.egorp.finance.dao.Currency;
import by.xo.egorp.finance.dao.CurrencyDao;
import by.xo.egorp.finance.dao.DaoSession;
import by.xo.egorp.finance.dao.Wallet;
import by.xo.egorp.finance.dao.WalletDao;
import by.xo.egorp.finance.dao.WalletIcon;
import by.xo.egorp.finance.dao.WalletIconDao;

public class ManagementOfWallets {
    private DaoSession daoSession;
    private WalletIconDao walletIconDao;
    private CurrencyDao currencyDao;
    private WalletDao walletDao;

    private List<WalletIcon> walletIcons;
    private List<Currency> currencies;
    private List<Wallet> wallets;

    public ManagementOfWallets() {
        this.daoSession = AppController.getDaoSession();

        walletIconDao = this.daoSession.getWalletIconDao();
        currencyDao = this.daoSession.getCurrencyDao();
        walletDao = this.daoSession.getWalletDao();

        getAllWalletIcons();
        getAllCurrencies();
        getAllWallets();
    }


    public void addWalletIcon(Integer walletPic) {
        WalletIcon tempWalletIcon = new WalletIcon();
        tempWalletIcon.setWalletPic(walletPic);
        walletIconDao.insert(tempWalletIcon);
    }

    public List<WalletIcon> getAllWalletIcons() {
        walletIcons = walletIconDao.loadAll();
        return walletIcons;
    }

    public void addCurrency(String currencyName, String currencyCode) {
        Currency tempCurrency = new Currency();
        tempCurrency.setCurrencyName(currencyName);
        tempCurrency.setCurrencyCode(currencyCode);

        currencyDao.insert(tempCurrency);
    }

    public List<Currency> getAllCurrencies() {
        currencies = currencyDao.loadAll();
        return currencies;
    }

    public void addWallet(String walletName, Currency walletCurrency,
                          Float walletBalance, WalletIcon walletIcon) {
        Wallet tempWallet = new Wallet();

        tempWallet.setWalletName(walletName);
        tempWallet.setCurrency(walletCurrency);
        tempWallet.setBalance(walletBalance);
        tempWallet.setWalletIcon(walletIcon);
        walletDao.insert(tempWallet);
    }

    public void delWallet(Wallet wallet) {
        walletDao.delete(wallet);
        getAllWallets();
    }

    public void delWallet(long id) {
        walletDao.deleteByKey(id);
        getAllWallets();
    }


    public List<Wallet> getAllWallets() {
        wallets = walletDao.loadAll();
        return wallets;
    }


}

