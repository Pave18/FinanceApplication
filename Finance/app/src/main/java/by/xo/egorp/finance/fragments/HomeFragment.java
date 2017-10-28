package by.xo.egorp.finance.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.adapters.AmountTotalAdapter;
import by.xo.egorp.finance.bal.AmountTotal;
import by.xo.egorp.finance.bal.ManagementOfWallets;


public class HomeFragment extends Fragment {

    ManagementOfWallets managementOfWallets;

    TextView tvMainCurrencyTotal;
    TextView tvMainCurrencyCode;

    ArrayList<AmountTotal> amountTotals;
    AmountTotal totalAmountMainCurrency;
    String mainCurrencyCod = "BYN";

    ListView lvAmountTotals;
    AmountTotalAdapter amountTotalAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, null);

        managementOfWallets = new ManagementOfWallets();

        amountTotals = new ArrayList<>();
        amountTotals.addAll(managementOfWallets.getAmountTotalByCurrencies(mainCurrencyCod));

        totalAmountMainCurrency = managementOfWallets.getTotalAmountMainCurrency();

        tvMainCurrencyTotal = v.findViewById(R.id.tvMainCurrencyTotal);
        tvMainCurrencyTotal.setText(totalAmountMainCurrency.getAmountTotal().toString());
        tvMainCurrencyCode = v.findViewById(R.id.tvMainCurrencyCode);
        tvMainCurrencyCode.setText(totalAmountMainCurrency.getCurrency().getCurrencyCode());

        amountTotalAdapter = new AmountTotalAdapter(HomeFragment.this.getActivity(), amountTotals);
        lvAmountTotals = v.findViewById(R.id.lvAmountTotals);
        lvAmountTotals.setAdapter(amountTotalAdapter);
        return v;
    }
}
