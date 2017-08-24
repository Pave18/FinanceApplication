package by.xo.egorp.finance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import by.xo.egorp.finance.AppController;
import by.xo.egorp.finance.R;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.adapters.WalletAdapter;
import by.xo.egorp.finance.dao.Wallet;

public class WalletsActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;
    private static final int CM_UPDATE_ID = 2;

    ManagementOfWallets managementOfWallets;

    ArrayList<Wallet> walletArrayList;
    WalletAdapter walletAdapter;

    ListView lvWallets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        managementOfWallets = new ManagementOfWallets(((AppController) getApplication()).getDaoSession());

        walletArrayList = new ArrayList<>();
        walletArrayList.addAll(managementOfWallets.getAllWallets());
        walletAdapter = new WalletAdapter(this, walletArrayList);

        lvWallets = (ListView) findViewById(R.id.lvWallets);
        lvWallets.setAdapter(walletAdapter);
        registerForContextMenu(lvWallets);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityAddNewWallets();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        fetchWalletList();
    }

    private void fetchWalletList() {
        walletArrayList.clear();
        walletArrayList.addAll(managementOfWallets.getAllWallets());
        walletAdapter.notifyDataSetChanged();
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

            startActivityAddNewWallets(walletArrayList.get(acmi.position));
            return true;
        }
        return super.onContextItemSelected(item);
    }


    //For create
    private void startActivityAddNewWallets() {
        Intent intent = new Intent(this, AddNewWalletActivity.class);
        intent.putExtra("create", true);
        startActivity(intent);
    }

    //For update
    private void startActivityAddNewWallets(Wallet wallet) {
        Intent intent = new Intent(this, AddNewWalletActivity.class);
        intent.putExtra("create", false);
        intent.putExtra("wallet", (Parcelable) wallet);
        startActivity(intent);
    }

}
