package by.xo.egorp.finance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

    EditText editTextWalletName;
    Spinner spinnerCurrencies;
    AdapterCurrency adapterCurrency;
    EditText editTextBalance;
    Spinner spinnerWalletIcons;
    AdapterWalletIcon adapterWalletIcon;
    Button buttonSaveWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_wallet);

        managementOfWallets = new ManagementOfWallets();
        wallet = new Wallet();

        createNew = true;

        editTextWalletName = (EditText) findViewById(R.id.wallet_name);
        spinnerCurrencies = (Spinner) findViewById(R.id.spinner_currencies);
        editTextBalance = (EditText) findViewById(R.id.balance);
        spinnerWalletIcons = (Spinner) findViewById(R.id.spinner_walletIcons);
        buttonSaveWallet = (Button) findViewById(R.id.btn_save_wallet);

        ArrayList<Currency> arrayAdapterCurrency =
                (ArrayList<Currency>) managementOfWallets.getAllCurrencies();
        adapterCurrency = new AdapterCurrency(this, arrayAdapterCurrency);
        spinnerCurrencies.setAdapter(adapterCurrency);

        ArrayList<WalletIcon> arrayAdapterWalletIcon =
                (ArrayList<WalletIcon>) managementOfWallets.getAllWalletIcons();
        adapterWalletIcon = new AdapterWalletIcon(this, arrayAdapterWalletIcon);
        spinnerWalletIcons.setAdapter(adapterWalletIcon);

        handleIntent(getIntent());
        setClickEventListener();
    }

    private void handleIntent(Intent intent) {
        createNew = intent.getBooleanExtra("create", false);

        if (!createNew) {
            wallet = (Wallet) intent.getSerializableExtra("wallet");
            editTextWalletName.setText(wallet.getWalletName());
            spinnerCurrencies.setSelection(
                    Integer.parseInt((wallet.getCurrency().getId()).toString()));
            editTextBalance.setText(wallet.getBalance().toString());
            spinnerWalletIcons.setSelection(
                    Integer.parseInt(wallet.getWalletIcon().getId().toString()));
        }
    }

    private void setClickEventListener() {
        buttonSaveWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createNew) {
                    saveItem(getResources().getString(R.string.title_wallet_insert));
                } else {
                    saveItem(getResources().getString(R.string.title_wallet_updated));
                }
            }
        });
    }

    private void saveItem(String mess) {
        if (checkToFinish()) {
            managementOfWallets.addWallet(editTextWalletName.getText().toString(),
                    (Currency) spinnerCurrencies.getSelectedItem(),
                    Float.parseFloat(editTextBalance.getText().toString()),
                    (WalletIcon) spinnerWalletIcons.getSelectedItem());
            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    boolean checkToFinish() {
        if (editTextWalletName.getText().length() != 0 &&
                spinnerCurrencies.getSelectedItem() != null &&
                editTextWalletName.getText().length() != 0 &&
                spinnerWalletIcons.getSelectedItem() != null) {
            return true;
        }
        return false;
    }
}
