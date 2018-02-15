package by.xo.egorp.finance.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.FinanceTransaction;


public class MoneyTransfersAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<FinanceTransaction> financeTransactions;

    public MoneyTransfersAdapter(Context context, ArrayList<FinanceTransaction> transactions) {
        ctx = context;
        financeTransactions = transactions;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return financeTransactions.size();
    }

    @Override
    public Object getItem(int position) {
        return financeTransactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return financeTransactions.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_money_transfer, parent, false);
        }

        FinanceTransaction tempFinanceTransaction = getFinanceTransaction(position);

        ((ImageView) view.findViewById(R.id.ivMoney_transfer_wallet_pic))
                .setImageResource(tempFinanceTransaction.getWallet().getWalletIcon().getWalletPic());

        if (tempFinanceTransaction.getSubcategory() == null) {
            ((TextView) view.findViewById(R.id.tvMoney_transfer_category))
                    .setText(tempFinanceTransaction.getCategory().getCategoryName());
        } else {
            ((TextView) view.findViewById(R.id.tvMoney_transfer_category))
                    .setText(tempFinanceTransaction.getSubcategory().getSubcategoryName());
        }

        ((TextView) view.findViewById(R.id.tvMoney_transfer_amount))
                .setText(tempFinanceTransaction.getAmount().toString());
        if (tempFinanceTransaction.getTransactionType()) {
            ((TextView) view.findViewById(R.id.tvMoney_transfer_amount))
                    .setTextColor(Color.parseColor("#72d613"));
        } else {
            ((TextView) view.findViewById(R.id.tvMoney_transfer_amount))
                    .setTextColor(Color.parseColor("#d6133a"));
        }

        ((TextView) view.findViewById(R.id.tvMoneyTransferWalletCurrency))
                .setText(tempFinanceTransaction.getWallet().getCurrency().getCurrencyCode());

        ((TextView) view.findViewById(R.id.tvMoney_transfer_date))
                .setText(parseDate(tempFinanceTransaction.getData()));

        return view;
    }

    FinanceTransaction getFinanceTransaction(int position) {
        return (FinanceTransaction) getItem(position);
    }

    String parseDate(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String tempString = null;
        try {
            tempString = df.format(date);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return tempString;
    }
}

