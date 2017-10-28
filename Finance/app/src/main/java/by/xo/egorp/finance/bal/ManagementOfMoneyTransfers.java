package by.xo.egorp.finance.bal;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.xo.egorp.finance.AppController;
import by.xo.egorp.finance.dao.Category;
import by.xo.egorp.finance.dao.DaoSession;
import by.xo.egorp.finance.dao.FinanceTransaction;
import by.xo.egorp.finance.dao.FinanceTransactionDao;
import by.xo.egorp.finance.dao.Subcategory;
import by.xo.egorp.finance.dao.Wallet;

public class ManagementOfMoneyTransfers {

    private DaoSession daoSession;
    private FinanceTransactionDao financeTransactionDao;
    private List<FinanceTransaction> financeTransactions;

    public ManagementOfMoneyTransfers() {
        daoSession = AppController.getDaoSession();
        financeTransactionDao = daoSession.getFinanceTransactionDao();
        financeTransactions = new ArrayList<>();
        getAllMoneyTransfers();
    }

    public void addMoneyTransfers(boolean type, Wallet wallet, float amount, Date date, String description,
                                  Byte photo, Category category, Subcategory subcategory) {
        FinanceTransaction tempFinanceTransaction = new FinanceTransaction();


        tempFinanceTransaction.setTransactionType(type);
        if (type)
            tempFinanceTransaction.setAmount(0 + amount);
        else
            tempFinanceTransaction.setAmount(0 - amount);
        tempFinanceTransaction.setData(date);
        tempFinanceTransaction.setDescription(description);
        tempFinanceTransaction.setPhoto(photo);
        tempFinanceTransaction.setWallet(wallet);
        tempFinanceTransaction.setCategory(category);
        tempFinanceTransaction.setSubcategory(subcategory);

        financeTransactionDao.insert(tempFinanceTransaction);
    }

    public void delMoneyTransfers(FinanceTransaction financeTransaction) {
        financeTransactionDao.delete(financeTransaction);
        getAllMoneyTransfers();
    }


    public List<FinanceTransaction> getAllMoneyTransfers() {
        financeTransactions = financeTransactionDao.loadAll();
        return financeTransactions;
    }
}
