package by.xo.egorp.finance.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.adapters.AdapterWallet;
import by.xo.egorp.finance.bal.ManagementOfCategories;
import by.xo.egorp.finance.bal.ManagementOfMoneyTransfers;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.FinanceTransaction;
import by.xo.egorp.finance.dao.Wallet;

public class FragmentIncomeAndExpenditure extends Fragment {

    ManagementOfWallets managementOfWallets;
    ManagementOfCategories managementOfCategories;
    ManagementOfMoneyTransfers managementOfMoneyTransfers;

    FinanceTransaction financeTransaction;
    boolean createNew;
    boolean transactionType;

    TextView tvType;

    Spinner spinnerWallets;
    AdapterWallet adapterWallet;
    EditText editTextAmount;
    //AdapterCategory adapterCategory;
    Spinner spinnerCategory;
    EditText editTextDate;
    EditText editTextDescription;
    Button btnAddPhoto;

    Button buttonSaveTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_income_and_expenditure, null);

        managementOfWallets = new ManagementOfWallets();
        managementOfCategories = new ManagementOfCategories();
        managementOfMoneyTransfers = new ManagementOfMoneyTransfers();
        financeTransaction = new FinanceTransaction();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            transactionType = bundle.getBoolean("TypeTransaction");
        }

        tvType = v.findViewById(R.id.tvTypeTrans);
        if (transactionType) {
            tvType.setText("Income!!!!");
            tvType.setTextColor(Color.parseColor("#72d613"));
        } else {
            tvType.setText("Expanded!!!!");
            tvType.setTextColor(Color.parseColor("#d6133a"));
        }

        createNew = true;

        spinnerWallets = v.findViewById(R.id.spinner_wallet);
        ArrayList<Wallet> arrayAdapterWallets =
                (ArrayList<Wallet>) managementOfWallets.getAllWallets();
        adapterWallet = new AdapterWallet(FragmentIncomeAndExpenditure.this.getActivity(), arrayAdapterWallets);
        spinnerWallets.setAdapter(adapterWallet);

       /* spinnerCategory = v.findViewById(R.id.spinner_category);
        ArrayList<Category> arrayCategory =
                (ArrayList<Category>) managementOfCategories.getAllCategories();
        adapterCategory = new AdapterCategory(FragmentIncomeAndExpenditure.this.getActivity(), arrayCategory);
        spinnerCategory.setAdapter(adapterCategory);*/


        editTextAmount = v.findViewById(R.id.etAmount);
        editTextDate = v.findViewById(R.id.etDate);
        editTextDescription = v.findViewById(R.id.etDescription);
        buttonSaveTransaction = v.findViewById(R.id.btn_save_transaction);

        //handleIntent(getIntent());
        setClickEventListener();

        return v;
    }



   /* private void handleIntent(Intent intent) {
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
    }*/

    private void setClickEventListener() {
        buttonSaveTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createNew) {
                    saveItem(getResources().getString(R.string.title_transaction_insert));
                } else {
                    saveItem(getResources().getString(R.string.title_transaction_updated));
                }
            }
        });
    }

    private void saveItem(String mess) {
        if (checkToFinish()) {
            addTransaction();
            Toast.makeText(FragmentIncomeAndExpenditure.this.getActivity(), mess, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private boolean checkToFinish() {
        if (editTextAmount.getText().length() != 0 &&
                spinnerWallets.getSelectedItem() != null &&
                editTextDate.getText().length() != 0) {
            return true;
        }
        return false;
    }

    private void addTransaction() {
        managementOfMoneyTransfers.addMoneyTransfers(transactionType,
                (Wallet) spinnerWallets.getSelectedItem(),
                Float.parseFloat(editTextAmount.getText().toString()),
                editTextDate.getText().toString(),
                editTextDescription.getText().toString(),
                (byte) 0,
                managementOfCategories.getAllCategories().get(0),
                null);

        managementOfWallets.changeOfBalance(transactionType,
                (Wallet) spinnerWallets.getSelectedItem(),
                Float.parseFloat(editTextAmount.getText().toString()));
    }
}
