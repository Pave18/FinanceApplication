package by.xo.egorp.finance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.WalletIcon;

public class AdapterWalletIcon extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<WalletIcon> walletIconList;

    public AdapterWalletIcon(Context context, ArrayList<WalletIcon> walletIcons) {
        ctx = context;
        walletIconList = walletIcons;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return walletIconList.size();
    }

    @Override
    public Object getItem(int position) {
        return walletIconList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return walletIconList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_wallet_icon, parent, false);
        }

        WalletIcon tempWalletIcon = getWalletIcon(position);

        ((ImageView) view.findViewById(R.id.ivWalletIcon))
                .setImageResource(tempWalletIcon.getWalletPic());

        return view;
    }

    WalletIcon getWalletIcon(int position) {
        return (WalletIcon) getItem(position);
    }
}
