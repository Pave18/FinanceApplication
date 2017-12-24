package by.xo.egorp.finance.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import by.xo.egorp.finance.R;

public class TransfersFragment extends Fragment implements View.OnClickListener {

    Button buttonSaveTransaction;
    boolean createNew;

    public static TransfersFragment newInstance(boolean newTransaction) {

        Bundle args = new Bundle();
        args.putBoolean("CreateNewTransaction", newTransaction);

        TransfersFragment fragment = new TransfersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            createNew = bundle.getBoolean("CreateNewTransaction");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transfers, null);

        buttonSaveTransaction = v.findViewById(R.id.btn_save_transfer_transaction);
        buttonSaveTransaction.setOnClickListener(this);

        readBundle(getArguments());

        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_transaction:
                if (createNew) {
                    saveItem(getResources().getString(R.string.title_transaction_insert));
                } else {
                    saveItem(getResources().getString(R.string.title_transaction_updated));
                }
                break;
        }
    }


    private void saveItem(String mess) {
        if (checkToFinish()) {
            ///addTransaction();
            Toast.makeText(TransfersFragment.this.getActivity(), mess, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    private boolean checkToFinish() {
        if (true) {
            return true;
        }
        return false;
    }

    private void addTransaction() {

    }


}
