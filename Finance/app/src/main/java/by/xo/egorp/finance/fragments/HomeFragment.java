package by.xo.egorp.finance.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.ShadowTransformer;
import by.xo.egorp.finance.adapters.AmountTotalAdapter;
import by.xo.egorp.finance.adapters.WalletCardPagerAdapter;
import by.xo.egorp.finance.bal.AmountTotal;
import by.xo.egorp.finance.bal.ManagementOfWallets;


public class HomeFragment extends Fragment {

    ManagementOfWallets managementOfWallets;

    private ViewPager mViewPager;
    private WalletCardPagerAdapter walletCardPagerAdapter;
    private ShadowTransformer mCardShadowTransformer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, null);

        managementOfWallets = new ManagementOfWallets();

        mViewPager = v.findViewById(R.id.viewPager);

        walletCardPagerAdapter = new WalletCardPagerAdapter();
        walletCardPagerAdapter.addCardItem(managementOfWallets.getAllWallets());

        mCardShadowTransformer = new ShadowTransformer(mViewPager, walletCardPagerAdapter);
        mCardShadowTransformer.enableScaling(true);

        mViewPager.setAdapter(walletCardPagerAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        return v;
    }
}
