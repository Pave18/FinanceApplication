package by.xo.egorp.finance.bal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
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
        return walletIconDao.loadAll();
    }

    private int firstItem = 0;

    public WalletIcon getFirstWalletIcon() {
        return getAllWalletIcons().get(firstItem);
    }

    public void addCurrency(String currencyName, String currencyCode) {
        Currency tempCurrency = new Currency();
        tempCurrency.setCurrencyName(currencyName);
        tempCurrency.setCurrencyCode(currencyCode);

        currencyDao.insert(tempCurrency);
    }

    public List<Currency> getAllCurrencies() {
        return currencyDao.loadAll();
    }

    public Currency getFirstCurrency() {
        return getAllCurrencies().get(firstItem);
    }

    public Wallet findWalletById(long id) {

        Wallet tempWallet = new Wallet();

        for (Wallet w : getAllWallets()) {
            if (Objects.equals(w.getId(), id)) {
                tempWallet = w;
                break;
            }
        }

        return tempWallet;
    }

    public Wallet returnWallet(Boolean mainWallet, String nameWallet, Currency currencyWallet,
                               String balanceWallet, WalletIcon iconWallet, Integer backgroundWallet) {
        Float tempBalance = round(Float.parseFloat(balanceWallet), 2);

        Wallet tempWallet = new Wallet();

        tempWallet.setMainWallet(findMainWallet(mainWallet));
        tempWallet.setWalletName(nameWallet);
        tempWallet.setBalance(tempBalance);
        tempWallet.setCurrency(currencyWallet);
        tempWallet.setWalletIcon(iconWallet);
        tempWallet.setBackground(backgroundWallet);

        return tempWallet;
    }

    public Wallet returnWallet(Wallet wallet, Boolean mainWallet, String nameWallet, Currency currencyWallet,
                               String balanceWallet, WalletIcon iconWallet, Integer backgroundWallet) {
        Float tempBalance = round(Float.parseFloat(balanceWallet), 2);


        wallet.setMainWallet(findMainWallet(mainWallet));
        wallet.setWalletName(nameWallet);
        wallet.setBalance(tempBalance);
        wallet.setCurrency(currencyWallet);
        wallet.setWalletIcon(iconWallet);
        wallet.setBackground(backgroundWallet);

        return wallet;
    }

    public void addWallet(Boolean mainWallet, String nameWallet, Currency currencyWallet,
                          String balanceWallet, WalletIcon iconWallet, Integer backgroundWallet) {


        Float tempBalance = round(Float.parseFloat(balanceWallet), 2);

        Wallet tempWallet = new Wallet();

        tempWallet.setMainWallet(findMainWallet(mainWallet));
        tempWallet.setWalletName(nameWallet);
        tempWallet.setBalance(tempBalance);
        tempWallet.setCurrency(currencyWallet);
        tempWallet.setWalletIcon(iconWallet);
        tempWallet.setBackground(backgroundWallet);

        walletDao.insert(tempWallet);
    }

    public void addWallet(Wallet wallet) {
        walletDao.insert(wallet);
    }

    public void updateWallet(Wallet wallet, Boolean mainWallet, String nameWallet, Currency currencyWallet,
                             String balanceWallet, WalletIcon iconWallet, Integer backgroundWallet) {


        Float tempBalance = round(Float.parseFloat(balanceWallet), 2);

        wallet.setMainWallet(findMainWallet(mainWallet));
        wallet.setWalletName(nameWallet);
        wallet.setBalance(tempBalance);
        wallet.setCurrency(currencyWallet);
        wallet.setWalletIcon(iconWallet);
        wallet.setBackground(backgroundWallet);

        walletDao.update(wallet);
    }

    public void updateWallet(Wallet wallet) {
        walletDao.update(wallet);
    }

    private static float round(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++)
            pow *= 10;
        float tmp = number * pow;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / pow;
    }

    private boolean findMainWallet(boolean switchMain) {

        if (getAllWallets().size() == 0) {
            return true;
        } else if (switchMain && getAllWallets().size() != 0) {
            for (Wallet w : getAllWallets()) {
                if (w.getMainWallet()) {
                    w.setMainWallet(false);
                    walletDao.update(w);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void removeWallet(Wallet wallet) {
        walletDao.delete(wallet);
        getAllWallets();
    }


    public List<Wallet> getAllWallets() {
        List<Wallet> tempWalletsList = walletDao.loadAll();

        List<Wallet> sortedWallets = new ArrayList<>();

        //Search for the main purse and transfer it to the first position.
        if (tempWalletsList.size() != 1) {
            for (Wallet w : tempWalletsList) {
                if (w.getMainWallet()) {
                    Wallet wallet = w;
                    tempWalletsList.remove(w);
                    sortedWallets.add(w);
                    sortedWallets.addAll(tempWalletsList);
                    return sortedWallets;

                }
            }
        }


        return walletDao.loadAll();
    }

    public void changeOfBalance(boolean income, Wallet wallet, float amount) {

        float newBalance = wallet.getBalance();

        if (income) {
            newBalance += amount;
        } else {
            newBalance -= amount;
        }

        wallet.setBalance(newBalance);

        walletDao.update(wallet);
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