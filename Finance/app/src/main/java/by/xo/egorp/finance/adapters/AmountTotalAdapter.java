package by.xo.egorp.finance.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.bal.AmountTotal;

public class AmountTotalAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<AmountTotal> amountTotals;


    public AmountTotalAdapter(Context context, ArrayList<AmountTotal> amountTotals) {
        ctx = context;
        this.amountTotals = amountTotals;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return amountTotals.size();
    }

    @Override
    public Object getItem(int position) {
        return amountTotals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_amount_total, parent, false);
        }

        AmountTotal tempAmountTotal = getAmountTotal(position);

        ((TextView) view.findViewById(R.id.tvAmountTotalBalance))
                .setText(tempAmountTotal.getAmountTotal().toString());
        ((TextView) view.findViewById(R.id.tvAmountTotalCode))
                .setText(tempAmountTotal.getCurrency().getCurrencyCode());

        return view;
    }

    AmountTotal getAmountTotal(int position) {
        return (AmountTotal) getItem(position);
    }
}
