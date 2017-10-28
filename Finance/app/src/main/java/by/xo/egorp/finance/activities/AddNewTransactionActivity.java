package by.xo.egorp.finance.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.adapters.FragmentAdapter;
import by.xo.egorp.finance.fragments.HomeFragment;
import by.xo.egorp.finance.fragments.IncomeAndExpenditureFragment;
import by.xo.egorp.finance.fragments.TransfersFragment;

public class AddNewTransactionActivity extends AppCompatActivity {

    boolean createNewFragment;

    TabLayout mTabLayout;
    ViewPager mViewPager;

    List<Fragment> fragments;
    int idFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAdd_transaction);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.tab_title_income));
        titles.add(getString(R.string.tab_title_expenses));
        titles.add(getString(R.string.tab_title_transfers));

        createNewFragment = true;

        fragments = new ArrayList<>();
        fragments.add(new IncomeAndExpenditureFragment(true, createNewFragment));
        fragments.add(new IncomeAndExpenditureFragment(false, createNewFragment));
        fragments.add(new TransfersFragment(createNewFragment));

        idFragment = 0;
        initViewPager(titles, fragments);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.action_save_wallet) {
            fragments.get(idFragment).getView().findViewById(R.id.btn_save_transaction).callOnClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViewPager(List<String> titles, List<Fragment> fragments) {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout_transaction);
        mViewPager = (ViewPager) findViewById(R.id.view_pager_transaction);

        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));

        mViewPager.setOffscreenPageLimit(2);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mFragmentAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                idFragment = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private Fragment getFragmentFromTransaction(Class fragmentClass, boolean type) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bundle bundle = new Bundle();
        bundle.putBoolean("TypeTransaction", type);
        fragment.setArguments(bundle);

        return fragment;
    }
}
