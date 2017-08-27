package by.xo.egorp.finance.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.adapters.AdapterAmountTotal;
import by.xo.egorp.finance.bal.AmountTotal;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.Currency;


public class FragmentHome extends Fragment {

    ManagementOfWallets managementOfWallets;

    TextView tvMainCurrencyTotal;
    TextView tvMainCurrencyCode;

    ArrayList<AmountTotal> amountTotals;
    String mainCurrencyCod = "BYN";
    Currency mainCurrency;
    Float mainTotal;

    ListView lvAmountTotals;
    AdapterAmountTotal adapterAmountTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, null);

        managementOfWallets = new ManagementOfWallets();

        amountTotals = new ArrayList<>();
        amountTotals.addAll(managementOfWallets.getAmountTotalByCurrencies());

        searchMainCurrencyTotal(mainCurrencyCod);

        tvMainCurrencyTotal = v.findViewById(R.id.tvMainCurrencyTotal);
        tvMainCurrencyTotal.setText(mainTotal.toString());
        tvMainCurrencyCode = v.findViewById(R.id.tvMainCurrencyCode);
        tvMainCurrencyCode.setText(mainCurrency.getCurrencyCode());

        adapterAmountTotal = new AdapterAmountTotal(FragmentHome.this.getActivity(), amountTotals);
        lvAmountTotals = v.findViewById(R.id.lvAmountTotals);
        lvAmountTotals.setAdapter(adapterAmountTotal);
        return v;
    }

    private void searchMainCurrencyTotal(String mainCurrencyCode) {
        List<Currency> currencies = new ArrayList<>();
        List<Float> totals = new ArrayList<>();

        for (AmountTotal at : amountTotals) {
            currencies.add(at.getCurrency());
            totals.add(at.getAmountTotal());
        }

        for (int i = 0; i < currencies.size(); ++i) {
            if (currencies.get(i).getCurrencyCode().equals(mainCurrencyCode)) {

                mainCurrency = currencies.get(i);
                mainTotal = totals.get(i);

                amountTotals.remove(i);
                break;
            }
        }
    }

}
