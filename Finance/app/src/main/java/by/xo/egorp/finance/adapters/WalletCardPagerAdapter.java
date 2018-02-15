package by.xo.egorp.finance.adapters;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.Wallet;
import by.xo.egorp.finance.dialogs.SettingWalletDialog;

public class WalletCardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<Wallet> mData;
    private float mBaseElevation;
    FragmentManager ft;


    public WalletCardPagerAdapter(FragmentManager fragmentTransaction) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        ft = fragmentTransaction;
    }

    public void addCardItems(List<Wallet> item) {

        for (Wallet w : item) {
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
        if (position != mData.size() - 1) {
            bind(mData.get(position), view, false);
        } else {
            bind(mData.get(position), view, true);
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
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
                    Long id = item.getId();
                    if (id != null) {
                        showDialog(false, id);
                    }
                }
            });
        }
    }

    private void showDialog(boolean createNew, Long id) {
        DialogFragment settingWalletDialog;
        if (createNew) {
            settingWalletDialog = SettingWalletDialog.newInstance();
        } else {
            settingWalletDialog = SettingWalletDialog.newInstance(id);
        }
        settingWalletDialog.show(ft, "SettingWalletDialog");
    }
}
