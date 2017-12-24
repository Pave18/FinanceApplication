package by.xo.egorp.finance.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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
import by.xo.egorp.finance.adapters.WalletAdapter;
import by.xo.egorp.finance.bal.ManagementOfCategories;
import by.xo.egorp.finance.bal.ManagementOfMoneyTransfers;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.FinanceTransaction;
import by.xo.egorp.finance.dao.Wallet;
import by.xo.egorp.finance.dialogs.CalendarDialog;

public class IncomeAndExpenditureFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_DATE = 1;

    Button buttonSaveTransaction;
    boolean createNew;
    boolean transactionType;

    ManagementOfWallets managementOfWallets;
    ManagementOfCategories managementOfCategories;
    ManagementOfMoneyTransfers managementOfMoneyTransfers;

    DialogFragment dialogFragment;
    CalendarDialog calendarDialog;

    Spinner spinnerWallets;
    WalletAdapter walletAdapter;
    EditText etAmount;
    //AdapterCategory adapterCategory;
    Spinner spinnerCategory;
    TextView tvDate;
    EditText etDescription;
    Button btnCamera, btnGallery;

    public static IncomeAndExpenditureFragment newInstance(boolean newTransaction, boolean typeTransaction) {
        Bundle args = new Bundle();
        args.putBoolean("CreateNewTransaction", newTransaction);
        args.putBoolean("TypeTransaction", typeTransaction);

        IncomeAndExpenditureFragment fragment = new IncomeAndExpenditureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            createNew = bundle.getBoolean("CreateNewTransaction");
            transactionType = bundle.getBoolean("TypeTransaction");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_income_and_expenditure, null);

        buttonSaveTransaction = v.findViewById(R.id.btn_save_transaction);
        buttonSaveTransaction.setOnClickListener(this);

        readBundle(getArguments());

        managementOfWallets = new ManagementOfWallets();
        managementOfCategories = new ManagementOfCategories();
        managementOfMoneyTransfers = new ManagementOfMoneyTransfers();

        calendarDialog = new CalendarDialog();


        spinnerWallets = v.findViewById(R.id.spinner_wallet);
        ArrayList<Wallet> arrayAdapterWallets = (ArrayList<Wallet>) managementOfWallets.getAllWallets();
        walletAdapter = new WalletAdapter(IncomeAndExpenditureFragment.this.getActivity(), arrayAdapterWallets);
        spinnerWallets.setAdapter(walletAdapter);

       /* spinnerCategory = v.findViewById(R.id.spinner_category);
        ArrayList<Category> arrayCategory =
                (ArrayList<Category>) managementOfCategories.getAllCategories();
        adapterCategory = new AdapterCategory(IncomeAndExpenditureFragment.this.getActivity(), arrayCategory);
        spinnerCategory.setAdapter(adapterCategory);*/


        etAmount = v.findViewById(R.id.etAmount);
        tvDate = v.findViewById(R.id.tvDate);
        tvDate.setOnClickListener(this);
        tvDate.setText(calendarDialog.getDateToString());
        etDescription = v.findViewById(R.id.etDescription);


        //handleIntent();

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDate:
                dialogFragment = calendarDialog;
                dialogFragment.setTargetFragment(this, REQUEST_DATE);
                dialogFragment.show(getFragmentManager(), "CalendarDialog");
                break;
            case R.id.btn_save_transaction:
                if (createNew) {
                    saveItem(getResources().getString(R.string.title_transaction_insert));
                } else {
                    saveItem(getResources().getString(R.string.title_transaction_updated));
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_DATE:
                    tvDate.setText(data.getStringExtra(CalendarDialog.TAG_DATE_SELECTED));
                    break;
            }
        }
    }

   /* private void handleIntent() {
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

    private void saveItem(String mess) {
        if (checkToFinish()) {
            addTransaction();
            Toast.makeText(IncomeAndExpenditureFragment.this.getActivity(), mess, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private boolean checkToFinish() {
        if (etAmount.getText().length() != 0 &&
                spinnerWallets.getSelectedItem() != null &&
                tvDate.getText().length() != 0) {
            return true;
        }
        return false;
    }

    private void addTransaction() {
        managementOfMoneyTransfers.addMoneyTransfers(transactionType,
                (Wallet) spinnerWallets.getSelectedItem(),
                Float.parseFloat(etAmount.getText().toString()),
                calendarDialog.getDate(),
                etDescription.getText().toString(),
                (byte) 0,
                managementOfCategories.getAllCategories().get(0),
                null);
    }
}
