package by.xo.egorp.finance.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.Wallet;
import by.xo.egorp.finance.dialogs.SettingWalletDialog;

public class WalletCardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<Wallet> mData;
    private float mBaseElevation;
    private FragmentManager fragmentManager;

    private SettingWalletDialog settingWalletDialog;

    public WalletCardPagerAdapter(FragmentManager fragmentManager, SettingWalletDialog settingWalletDialog) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        this.settingWalletDialog = settingWalletDialog;
        this.fragmentManager = fragmentManager;
    }

    public void addWallets(List<Wallet> wallets) {

        for (Wallet w : wallets) {
            mViews.add(null);
            mData.add(w);
        }

        //add end element
        mViews.add(null);
        mData.add(new Wallet());
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    public Wallet getWalletAt(int position) {
        return mData.get(position);
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.wallet_card_adapter, container, false);

        container.addView(view);

        if (position != mViews.size() - 1) {
            bind(mData.get(position), view, false);
        } else {
            bind(null, view, true);
        }
        CardView cardView = view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        if (mViews.contains(object)) {
            return mViews.indexOf(object);
        } else {
            return POSITION_NONE;
        }
    }

    private void bind(final Wallet item, View view, boolean endElement) {

        TextView walletName = view.findViewById(R.id.nameWalletTextView);
        TextView balance = view.findViewById(R.id.balanceTextView);
        TextView currencyCode = view.findViewById(R.id.codeTextView);
        TextView total = view.findViewById(R.id.totalWalletTextView);

        ImageView star = view.findViewById(R.id.walletStarImageView);
        ImageView setting = view.findViewById(R.id.walletSettingImageView);
        ImageView walletType = view.findViewById(R.id.walletTypeImageView);
        ImageView walletIcon = view.findViewById(R.id.walletIconImageView);

        ImageView addWallet = view.findViewById(R.id.addWalletImageButton);

        if (endElement) {
            walletName.setVisibility(View.INVISIBLE);
            balance.setVisibility(View.INVISIBLE);
            currencyCode.setVisibility(View.INVISIBLE);
            total.setVisibility(View.INVISIBLE);

            star.setVisibility(View.INVISIBLE);
            setting.setVisibility(View.INVISIBLE);
            walletType.setVisibility(View.INVISIBLE);
            walletIcon.setVisibility(View.INVISIBLE);

            addWallet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(true, null);
                }
            });
        } else {
            addWallet.setVisibility(View.INVISIBLE);

            walletName.setText(item.getWalletName());
            total.setText(item.getBalance().toString());
            currencyCode.setText(item.getCurrency().getCurrencyCode());

            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(false, item);
                }
            });
        }
    }

    private void showDialog(boolean createNew, final Wallet wallet) {
        Bundle args = new Bundle();
        args.putBoolean("CreateNew", createNew);
        if (wallet != null)
            args.putLong("IdWallet", wallet.getId());

        settingWalletDialog.setArguments(args);
        settingWalletDialog.show(fragmentManager, "SettingWalletDialog");
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void remove(Wallet wallet) {
        mViews.remove(findPosition(wallet));
        mData.remove(wallet);
        notifyDataSetChanged();
    }

    public void update(Wallet wallet, List<Wallet> wallets) {
        if (wallet.getMainWallet()) {
            updateAll(wallets);
        } else {
            int position = findPositionById(wallet.getId());
            mData.set(position, wallet);
            mViews.set(position, null);
            notifyDataSetChanged();
        }
    }

    public void updateAll(List<Wallet> wallets) {

        removeAll();

        for (Wallet w : wallets) {
            mViews.add(null);
            mData.add(w);
        }

        //add end element
        mViews.add(null);
        mData.add(null);

        notifyDataSetChanged();
    }

    private void removeAll() {
        mViews = new ArrayList<>();
        mData = new ArrayList<>();
        notifyDataSetChanged();
    }


    private int findPosition(Wallet wallet) {
        int id = 0;

        for (; id < mData.size(); ) {
            if (wallet.equals(mData.get(id))) {
                break;
            }
            id++;
        }

        return id;
    }

    private int findPositionById(long walletId) {
        int id = 0;

        for (; id < mData.size(); ) {
            if (walletId == mData.get(id).getId()) {
                break;
            }
            id++;
        }

        return id;
    }


}