package by.xo.egorp.finance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.adapters.AdapterCurrency;
import by.xo.egorp.finance.adapters.AdapterWalletIcon;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.Currency;
import by.xo.egorp.finance.dao.Wallet;
import by.xo.egorp.finance.dao.WalletIcon;

public class ActivityAddNewWallet extends AppCompatActivity {

    ManagementOfWallets managementOfWallets;
    Wallet wallet;

    Boolean createNew;

    EditText etWalletName;
    Spinner spinCurrencies;
    AdapterCurrency adapterCurrency;
    EditText etBalance;
    Spinner spinnerWalletIcons;
    AdapterWalletIcon adapterWalletIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_wallet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAdd_wallets);

        setSupportActionBar(toolbar);

        managementOfWallets = new ManagementOfWallets();
        wallet = new Wallet();

        createNew = true;

        etWalletName = (EditText) findViewById(R.id.etWallet_name);
        spinCurrencies = (Spinner) findViewById(R.id.spinnerCurrencies);
        etBalance = (EditText) findViewById(R.id.etBalance);
        spinnerWalletIcons = (Spinner) findViewById(R.id.spinnerWallet_icons);

        ArrayList<Currency> arrayAdapterCurrency =
                (ArrayList<Currency>) managementOfWallets.getAllCurrencies();
        adapterCurrency = new AdapterCurrency(this, arrayAdapterCurrency);
        spinCurrencies.setAdapter(adapterCurrency);

        ArrayList<WalletIcon> arrayAdapterWalletIcon =
                (ArrayList<WalletIcon>) managementOfWallets.getAllWalletIcons();
        adapterWalletIcon = new AdapterWalletIcon(this, arrayAdapterWalletIcon);
        spinnerWalletIcons.setAdapter(adapterWalletIcon);

        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save_wallet) {
            if (createNew) {
                saveItem(getResources().getString(R.string.title_wallet_insert));
            } else {
                saveItem(getResources().getString(R.string.title_wallet_updated));
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {
        createNew = intent.getBooleanExtra("create", false);

        if (!createNew) {
            wallet = (Wallet) intent.getSerializableExtra("wallet");
            etWalletName.setText(wallet.getWalletName());
            spinCurrencies.setSelection(
                    Integer.parseInt((wallet.getCurrency().getId()).toString()));
            etBalance.setText(wallet.getBalance().toString());
            spinnerWalletIcons.setSelection(
                    Integer.parseInt(wallet.getWalletIcon().getId().toString()));
        }
    }

    private void saveItem(String mess) {
        if (checkToFinish()) {
            managementOfWallets.addWallet(etWalletName.getText().toString(),
                    (Currency) spinCurrencies.getSelectedItem(),
                    Float.parseFloat(etBalance.getText().toString()),
                    (WalletIcon) spinnerWalletIcons.getSelectedItem());
            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    boolean checkToFinish() {
        if (etWalletName.getText().length() != 0 &&
                spinCurrencies.getSelectedItem() != null &&
                etWalletName.getText().length() != 0 &&
                spinnerWalletIcons.getSelectedItem() != null) {
            return true;
        }
        return false;
    }
}
