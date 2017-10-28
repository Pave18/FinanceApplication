package by.xo.egorp.finance.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.xo.egorp.finance.R;

public class TransfersFragment extends Fragment {

    boolean createNew;

    public TransfersFragment(boolean createNew) {
        this.createNew = createNew;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfers, container, false);
    }

}
