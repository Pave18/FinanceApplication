package by.xo.egorp.finance.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.FinanceTransaction;

public class AdapterMoneyTransfers extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<FinanceTransaction> financeTransactions;

    public AdapterMoneyTransfers(Context context, ArrayList<FinanceTransaction> transactions) {
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
                .setText(tempFinanceTransaction.getData());

        return view;
    }

    FinanceTransaction getFinanceTransaction(int position) {
        return (FinanceTransaction) getItem(position);
    }
}
