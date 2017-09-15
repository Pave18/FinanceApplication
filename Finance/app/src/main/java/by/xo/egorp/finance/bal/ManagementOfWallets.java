package by.xo.egorp.finance.bal;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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


    public List<Wallet> getAllWallets() {
        wallets = walletDao.loadAll();
        return wallets;
    }

    public void changeOfBalance(boolean income, Wallet wallet, float amount) {

        Wallet tempWallet = wallet;
        float newBalance = tempWallet.getBalance();

        if (income) {
            newBalance += amount;
        } else {
            newBalance -= amount;
        }

        tempWallet.setBalance(newBalance);

        walletDao.update(tempWallet);
    }

    private AmountTotal totalAmountMainCurrency;

    public AmountTotal getTotalAmountMainCurrency() {
        return totalAmountMainCurrency;
    }

    public ArrayList<AmountTotal> getAmountTotalByCurrencies(String mainCurrencyCode) {
        List<Wallet> allWallets = getAllWallets();

        List<Currency> currencies = searchAllTypeCurrency(allWallets);
        List<Float> totals = amountTotalByCurrency(currencies, allWallets);


        ArrayList<AmountTotal> tempAmountTotals = new ArrayList<>();

        for (int i = 0; i < currencies.size(); ++i) {
            tempAmountTotals.add(new AmountTotal(currencies.get(i), totals.get(i)));
        }

        totalAmountMainCurrency = new AmountTotal();

        for (int i = 0; i < tempAmountTotals.size(); ++i) {
            if (tempAmountTotals.get(i).getCurrency().getCurrencyCode().equals(mainCurrencyCode)) {

                totalAmountMainCurrency.setCurrency(tempAmountTotals.get(i).getCurrency());
                totalAmountMainCurrency.setAmountTotal(tempAmountTotals.get(i).getAmountTotal());

                tempAmountTotals.remove(i);
                break;
            }
        }

        return tempAmountTotals;
    }

    //Search of all types of currencies  from all wallets
    private List<Currency> searchAllTypeCurrency(List<Wallet> allWallets) {

        List<Currency> tempCurrencies = new ArrayList<>();

        for (Wallet w : allWallets) {
            Currency tempCurrency = w.getCurrency();
            tempCurrencies.add(tempCurrency);
        }

        Set<Currency> uniqueCurrencies = new LinkedHashSet<>(tempCurrencies);

        tempCurrencies.clear();
        for (Currency c : uniqueCurrencies) {
            tempCurrencies.add(c);
        }

        return tempCurrencies;
    }

    //Total amount by currency from all wallets
    private List<Float> amountTotalByCurrency(List<Currency> currencies, List<Wallet> allWallets) {
        List<Float> tempTotals = new ArrayList<>();

        for (Currency c : currencies) {
            tempTotals.add(0F);
        }

        for (Wallet w : allWallets) {
            Currency tempCurrency = w.getCurrency();

            for (int i = 0; i < currencies.size(); ++i) {
                if (currencies.get(i).getCurrencyCode().
                        equals(tempCurrency.getCurrencyCode())) {
                    Float balance = tempTotals.get(i);
                    balance += w.getBalance();
                    tempTotals.set(i, balance);
                }
            }
        }

        return tempTotals;
    }



}