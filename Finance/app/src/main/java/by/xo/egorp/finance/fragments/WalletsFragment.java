package by.xo.egorp.finance.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.activities.AddNewTransactionActivity;
import by.xo.egorp.finance.adapters.MoneyTransfersAdapter;
import by.xo.egorp.finance.adapters.ShadowTransformer;
import by.xo.egorp.finance.adapters.WalletCardPagerAdapter;
import by.xo.egorp.finance.bal.ManagementOfMoneyTransfers;
import by.xo.egorp.finance.bal.ManagementOfWallets;
import by.xo.egorp.finance.dao.FinanceTransaction;
import by.xo.egorp.finance.dao.Wallet;
import by.xo.egorp.finance.dialogs.SettingWalletDialog;

public class WalletsFragment extends Fragment {
    private static final int CM_DELETE_ID = 1;
    private static final int CM_UPDATE_ID = 2;

    ManagementOfWallets managementOfWallets;
    ManagementOfMoneyTransfers managementOfMoneyTransfers;

    ArrayList<FinanceTransaction> financeTransactionArrayList;
    MoneyTransfersAdapter moneyTransfersAdapter;

    ViewPager mViewPager;
    WalletCardPagerAdapter walletCardPagerAdapter;
    ShadowTransformer mCardShadowTransformer;

    ListView lvMoneyTransfers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wallets, null);

        managementOfWallets = new ManagementOfWallets();
        managementOfMoneyTransfers = new ManagementOfMoneyTransfers();

        mViewPager = v.findViewById(R.id.viewPager);

        SettingWalletDialog settingWalletDialog = new SettingWalletDialog();
        settingWalletDialog.setOnClickListener(new SettingWalletDialog.EditWalletDialogListener() {
            @Override
            public void onFinishEditDialogFragment(int resultId, Wallet wallet) {
                if (resultId == SettingWalletDialog.CREATE_ID) {
                    managementOfWallets.addWallet(wallet);
                    walletCardPagerAdapter.updateAll(managementOfWallets.getAllWallets());
                } else if (resultId == SettingWalletDialog.UPDATE_ID) {
                    managementOfWallets.updateWallet(wallet);
                    walletCardPagerAdapter.update(wallet, managementOfWallets.getAllWallets());
                } else if (resultId == SettingWalletDialog.DELETE_ID) {
                    managementOfWallets.removeWallet(wallet);
                    walletCardPagerAdapter.remove(wallet);
                }
            }
        });

        walletCardPagerAdapter = new WalletCardPagerAdapter(getFragmentManager(), settingWalletDialog);
        walletCardPagerAdapter.addWallets(managementOfWallets.getAllWallets());


        mCardShadowTransformer = new ShadowTransformer(mViewPager, walletCardPagerAdapter);
        mCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(walletCardPagerAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Wallet tempWallet = walletCardPagerAdapter.getWalletAt(position);
                //Toast.makeText(getActivity(), tempWallet.getWalletName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        financeTransactionArrayList = new ArrayList<>();
        financeTransactionArrayList.addAll(managementOfMoneyTransfers.getAllMoneyTransfers());
        moneyTransfersAdapter = new MoneyTransfersAdapter
                (WalletsFragment.this.getActivity(), financeTransactionArrayList);

        lvMoneyTransfers = v.findViewById(R.id.lvMoney_transfers);
        lvMoneyTransfers.setAdapter(moneyTransfersAdapter);
        registerForContextMenu(lvMoneyTransfers);
        FloatingActionButton fab = v.findViewById(R.id.fabAdd_new_transfers);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityAddNewMoneyTransfer();
            }
        });

        Button button = v.findViewById(R.id.testButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        menu.add(0, CM_DELETE_ID, 0, R.string.title_transaction_delete);
        menu.add(0, CM_UPDATE_ID, 0, R.string.title_transaction_updated);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == CM_DELETE_ID) {
            FinanceTransaction tempFinanceTransaction = financeTransactionArrayList.get(acmi.position);

            managementOfWallets.changeOfBalance(false,
                    tempFinanceTransaction.getWallet(),
                    tempFinanceTransaction.getAmount());

            managementOfMoneyTransfers.delMoneyTransfers(tempFinanceTransaction);
            financeTransactionArrayList.remove(acmi.position);
            moneyTransfersAdapter.notifyDataSetChanged();
            return true;
        } else if (item.getItemId() == CM_UPDATE_ID) {
            startActivityAddNewMoneyTransfer(financeTransactionArrayList.get(acmi.position));
            return true;
        }

        return super.onContextItemSelected(item);
    }


    private void fetchWalletList() {
        financeTransactionArrayList.clear();
        financeTransactionArrayList.addAll(managementOfMoneyTransfers.getAllMoneyTransfers());
        moneyTransfersAdapter.notifyDataSetChanged();
    }

    //For create
    private void startActivityAddNewMoneyTransfer() {
        Intent intent = new Intent(WalletsFragment.this.getActivity(), AddNewTransactionActivity.class);
        intent.putExtra("create", true);
        startActivity(intent);
    }

    //For update
    private void startActivityAddNewMoneyTransfer(FinanceTransaction financeTransaction) {
        Intent intent = new Intent(WalletsFragment.this.getActivity(),
                AddNewTransactionActivity.class);
        intent.putExtra("create", false);
        intent.putExtra("wallet", (Parcelable) financeTransaction);
        startActivity(intent);
    }


}