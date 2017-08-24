package by.xo.egorp.finance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.Wallet;

public class WalletAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Wallet> walletArrayList;

    public WalletAdapter(Context context, ArrayList<Wallet> wallets) {
        ctx = context;
        walletArrayList = wallets;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return walletArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return walletArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return walletArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_wallet, parent, false);
        }

        Wallet tempWallet = getWallet(position);

        ((TextView) view.findViewById(R.id.tvWalletName)).setText(tempWallet.getWalletName());
        ((TextView) view.findViewById(R.id.tvBalance)).setText(tempWallet.getBalance().toString());
        ((TextView) view.findViewById(R.id.tvWalletCurrency)).setText(tempWallet.getCurrency().getCurrencyCode());
        ((ImageView) view.findViewById(R.id.ivWalletPic)).setImageResource(tempWallet.getWalletIcon().getWalletPic());

        return view;
    }

    Wallet getWallet(int position) {
        return (Wallet) getItem(position);
    }
}
