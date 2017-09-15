package by.xo.egorp.finance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.activities.ActivityAddNewTransaction;
import by.xo.egorp.finance.adapters.AdapterMoneyTransfers;
import by.xo.egorp.finance.bal.ManagementOfMoneyTransfers;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.FinanceTransaction;

public class FragmentMoneyTransfers extends Fragment {

    private static final int CM_DELETE_ID = 1;
    private static final int CM_UPDATE_ID = 2;

    ManagementOfWallets managementOfWallets;
    ManagementOfMoneyTransfers managementOfMoneyTransfers;

    ArrayList<FinanceTransaction> financeTransactionArrayList;
    AdapterMoneyTransfers adapterMoneyTransfers;

    ListView lvMoneyTransfers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_money_transfers, null);

        managementOfMoneyTransfers = new ManagementOfMoneyTransfers();
        managementOfWallets = new ManagementOfWallets();

        financeTransactionArrayList = new ArrayList<>();
        financeTransactionArrayList.addAll(managementOfMoneyTransfers.getAllMoneyTransfers());
        adapterMoneyTransfers = new AdapterMoneyTransfers
                (FragmentMoneyTransfers.this.getActivity(), financeTransactionArrayList);

        lvMoneyTransfers = v.findViewById(R.id.lvMoney_transfers);
        lvMoneyTransfers.setAdapter(adapterMoneyTransfers);
        registerForContextMenu(lvMoneyTransfers);
        FloatingActionButton fab = v.findViewById(R.id.fabAdd_new_transfers);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityAddNewMoneyTransfer();
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        fetchWalletList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, R.string.title_transaction_delete);
        menu.add(0, CM_UPDATE_ID, 0, R.string.title_transaction_updated);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == CM_DELETE_ID) {
            FinanceTransaction tempFinanceTransaction = financeTransactionArrayList.get(acmi.position);

            managementOfWallets.changeOfBalance(false,
                    tempFinanceTransaction.getWallet(),
                    tempFinanceTransaction.getAmount());

            managementOfMoneyTransfers.delMoneyTransfers(tempFinanceTransaction);
            financeTransactionArrayList.remove(acmi.position);
            adapterMoneyTransfers.notifyDataSetChanged();
            return true;
        } else if (item.getItemId() == CM_UPDATE_ID) {
            startActivityAddNewMoneyTransfer(financeTransactionArrayList.get(acmi.position));
            return true;
        }

        return super.onContextItemSelected(item);
    }


    private void fetchWalletList() {
        financeTransactionArrayList.clear();
        financeTransactionArrayList.addAll(managementOfMoneyTransfers.getAllMoneyTransfers());
        adapterMoneyTransfers.notifyDataSetChanged();
    }

    //For create
    private void startActivityAddNewMoneyTransfer() {
        Intent intent = new Intent(FragmentMoneyTransfers.this.getActivity(), ActivityAddNewTransaction.class);
        intent.putExtra("create", true);
        startActivity(intent);
    }

    //For update
    private void startActivityAddNewMoneyTransfer(FinanceTransaction financeTransaction) {
        Intent intent = new Intent(FragmentMoneyTransfers.this.getActivity(),
                ActivityAddNewTransaction.class);
        intent.putExtra("create", false);
        intent.putExtra("wallet", (Parcelable) financeTransaction);
        startActivity(intent);
    }
}
