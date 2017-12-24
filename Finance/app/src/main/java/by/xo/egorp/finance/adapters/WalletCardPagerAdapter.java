package by.xo.egorp.finance.adapters;


import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.dao.Wallet;

public class WalletCardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<Wallet> mData;
    private float mBaseElevation;

    public WalletCardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(Wallet item) {
        mViews.add(null);
        mData.add(item);
    }
    public void addCardItem(List<Wallet> item) {

        for (Wallet w: item
             ) {
            mViews.add(null);
            mData.add(w);
        }
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
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

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
    private void bind(Wallet item, View view) {
      TextView walletName = (TextView) view.findViewById(R.id.nameWalletTextView);
        TextView total = (TextView) view.findViewById(R.id.totalWalletTextView);
        TextView currencyCode = (TextView) view.findViewById(R.id.codeTextView);


        walletName.setText(item.getWalletName());
        total.setText(item.getBalance().toString());
        currencyCode.setText(item.getCurrency().getCurrencyCode());
    }
}
