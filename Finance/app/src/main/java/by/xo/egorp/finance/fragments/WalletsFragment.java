package by.xo.egorp.finance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.activities.AddNewWalletActivity;
import by.xo.egorp.finance.adapters.WalletAdapter;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.Wallet;

public class WalletsFragment extends Fragment {

    private static final int CM_DELETE_ID = 1;
    private static final int CM_UPDATE_ID = 2;

    ManagementOfWallets managementOfWallets;

    ArrayList<Wallet> walletArrayList;
    WalletAdapter walletAdapter;

    ListView lvWallets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallets, null);

        managementOfWallets = new ManagementOfWallets();

        walletArrayList = new ArrayList<>();
        walletArrayList.addAll(managementOfWallets.getAllWallets());
        walletAdapter = new WalletAdapter(WalletsFragment.this.getActivity(), walletArrayList);

        lvWallets = v.findViewById(R.id.lvWallets);
        lvWallets.setAdapter(walletAdapter);
        registerForContextMenu(lvWallets);

        FloatingActionButton fab = v.findViewById(R.id.fab_add_new_wallet);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityAddNewWallet();
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
        menu.add(0, CM_DELETE_ID, 0, R.string.title_wallet_delete);
        menu.add(0, CM_UPDATE_ID, 0, R.string.title_wallet_updated);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == CM_DELETE_ID) {

            managementOfWallets.delWallet(walletArrayList.get(acmi.position));
            walletArrayList.remove(acmi.position);
            walletAdapter.notifyDataSetChanged();

            return true;
        } else if (item.getItemId() == CM_UPDATE_ID) {

            startActivityAddNewWallet(walletArrayList.get(acmi.position));
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void fetchWalletList() {
        walletArrayList.clear();
        walletArrayList.addAll(managementOfWallets.getAllWallets());
        walletAdapter.notifyDataSetChanged();
    }

    //For create
    private void startActivityAddNewWallet() {
        Intent intent = new Intent(WalletsFragment.this.getActivity(), AddNewWalletActivity.class);
        intent.putExtra("create", true);
        startActivity(intent);
    }

    //For update
    private void startActivityAddNewWallet(Wallet wallet) {
        Intent intent = new Intent(WalletsFragment.this.getActivity(), AddNewWalletActivity.class);
        intent.putExtra("create", false);
        intent.putExtra("walletId", wallet.getId());
        startActivity(intent);
    }
}

