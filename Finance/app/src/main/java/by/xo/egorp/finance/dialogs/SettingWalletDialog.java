package by.xo.egorp.finance.dialogs;

import android.app.Dialog;

import android.content.DialogInterface;
import android.graphics.drawable.VectorDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.Currency;
import by.xo.egorp.finance.dao.Wallet;
import by.xo.egorp.finance.dao.WalletIcon;


public class SettingWalletDialog extends DialogFragment implements View.OnClickListener {

    ManagementOfWallets managementOfWallets;
    Wallet wallet;

    Boolean createNew;

    EditText nameWallet;
    ImageButton clearNameWallet;
    EditText balanceWallet;

    Switch mainWallet;
    Boolean mainW;
    TextView currencyWallet;
    Currency currencyW;
    ImageButton iconWallet;
    WalletIcon iconW;

    Integer backgroundWallet;


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

            if (!createNew) {
                wallet = managementOfWallets.findWalletById(bundle.getLong("IdWallet"));
            }
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        managementOfWallets = new ManagementOfWallets();
        wallet = new Wallet();

        readBundle(getArguments());

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (createNew) {
            builder.setTitle(R.string.action_new_wallet);
        } else {
            builder.setTitle(R.string.action_settings_wallet);
        }
        builder.setView(onCreateView());

        builder.setPositiveButton(R.string.title_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (createNew) {
                    saveItem(getString(R.string.title_wallet_insert));
                } else {
                    saveItem(getString(R.string.title_wallet_updated));
                }
            }
        });

        builder.setNegativeButton(R.string.title_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        if (!createNew) {
            builder.setNeutralButton(R.string.action_delete, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

                    alertDialog.setTitle(R.string.title_wallet_delete)
                            .setMessage(R.string.message_delete_wallet)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    managementOfWallets.delWallet(wallet);
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            });
        }

        return builder.create();
    }

    public View onCreateView() {
        View v =  getActivity().getLayoutInflater().inflate(R.layout.dialog_setting_wallet, null);
        currencyW = managementOfWallets.getFirstCurrency();
        iconW = managementOfWallets.getFirstWalletIcon();
        backgroundWallet = R.color.transparent;

        mainW = false;
        mainWallet = v.findViewById(R.id.switch_wallet_main);
        mainWallet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mainW = true;
                } else {
                    mainW = false;
                }
            }
        });

        nameWallet = v.findViewById(R.id.et_wallet_name);
        balanceWallet = v.findViewById(R.id.et_wallet_balance);

        clearNameWallet = v.findViewById(R.id.ib_clear_wallet_name);
        clearNameWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameWallet.setText("");
            }
        });

        currencyWallet = v.findViewById(R.id.tv_wallet_currency_cod);
        currencyWallet.setText(currencyW.getCurrencyCode());
        currencyWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        iconWallet = v.findViewById(R.id.ib_wallet_icon);
        iconWallet.setImageResource(iconW.getWalletPic());

        createCircleView();


        ifUpdate();
        return v;
    }

    private void createCircleView(){
      /*   check =R.drawable.ic_check_white_24dp;
        ImageView circleDefault = v.findViewById(R.id.circle_default);
        circleDefault.set(R.drawable.ic_check_white_24dp);*/
    }

    @Override
    public void onClick(View v) {

    }

    private void ifUpdate() {
        if (!createNew) {
            mainWallet.setChecked(wallet.getMainWallet());
            nameWallet.setText(wallet.getWalletName());
            balanceWallet.setText(wallet.getBalance().toString());
            currencyWallet.setText(wallet.getCurrency().getCurrencyCode());
            iconWallet.setImageResource(wallet.getWalletIcon().getWalletPic());


        }
    }

    private void saveItem(String mess) {
        if (checkToFinish()) {

            if (createNew) {
                managementOfWallets.addWallet(mainW, nameWallet.getText().toString(), currencyW,
                        balanceWallet.getText().toString(), iconW, backgroundWallet);
            } else {
                managementOfWallets.updateWallet(wallet, mainW, nameWallet.getText().toString(), currencyW,
                        balanceWallet.getText().toString(), iconW, backgroundWallet);
            }

            Toast.makeText(getActivity(), mess, Toast.LENGTH_SHORT).show();
        }
    }

    boolean checkToFinish() {
        if (nameWallet.getText().length() != 0 &&
                balanceWallet.getText().length() != 0
                ) {
            return true;
        } else
            return false;
    }
}
