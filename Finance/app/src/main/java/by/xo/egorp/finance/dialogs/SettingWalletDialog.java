package by.xo.egorp.finance.dialogs;

import android.app.Dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.Wallet;


public class SettingWalletDialog extends DialogFragment {

    ManagementOfWallets managementOfWallets;
    Wallet wallet;

    Boolean createNew;

    public static SettingWalletDialog newInstance() {

        Bundle args = new Bundle();
        args.putBoolean("CreateNew", true);

        SettingWalletDialog fragment = new SettingWalletDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static SettingWalletDialog newInstance(Long idWallet) {

        Bundle args = new Bundle();
        args.putBoolean("CreateNew", false);
        args.putLong("IdWallet", idWallet);

        SettingWalletDialog fragment = new SettingWalletDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            createNew = bundle.getBoolean("CreateNew");

            if(!createNew){
                wallet = managementOfWallets.findWalletFromId(bundle.getLong("IdWallet"));
            }
        }
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        managementOfWallets = new ManagementOfWallets();
        wallet = new Wallet();

        readBundle(getArguments());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (createNew) {
            builder.setTitle(R.string.action_new_wallet);
        } else {
            builder.setTitle(R.string.action_settings_wallet);
        }
        builder.setView(onCreateView());

        builder.setPositiveButton(R.string.title_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton(R.string.title_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    public View onCreateView() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_setting_wallet, null);

        return v;
    }

}
