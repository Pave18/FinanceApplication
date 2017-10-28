package by.xo.egorp.finance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.Currency;

public class CurrencyAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Currency> currencyArrayList;

    public CurrencyAdapter(Context context, ArrayList<Currency> currencies) {
        ctx = context;
        currencyArrayList = currencies;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return currencyArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return currencyArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_currency, parent, false);
        }

        Currency tempCurrency = getCurrency(position);

        ((TextView) view.findViewById(R.id.tvCurrencyName))
                .setText(tempCurrency.getCurrencyName());
        ((TextView) view.findViewById(R.id.tvCurrencyCode))
                .setText(tempCurrency.getCurrencyCode());

        return view;
    }

    Currency getCurrency(int position) {
        return (Currency) getItem(position);
    }
}
