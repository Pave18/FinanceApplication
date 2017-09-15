package by.xo.egorp.finance.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import by.xo.egorp.finance.R;
import by.xo.egorp.finance.fragments.FragmentIncomeAndExpenditure;

public class ActivityAddNewTransaction extends AppCompatActivity {

    Button btn_income, btn_expenses, btn_transfer;

    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAdd_transaction);

        setSupportActionBar(toolbar);

        btn_income = (Button) findViewById(R.id.btn_income);
        btn_expenses = (Button) findViewById(R.id.btn_expenses);
        btn_transfer = (Button) findViewById(R.id.btn_transfers);

        fragmentManager = getFragmentManager();
        fragment = getFragmentFromTransaction(FragmentIncomeAndExpenditure.class, true);

        fragmentManager.beginTransaction().replace(R.id.content_empty_transaction, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save_wallet) {
            fragment.getView().findViewById(R.id.btn_save_transaction).callOnClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_income:
                fragment = getFragmentFromTransaction(FragmentIncomeAndExpenditure.class, true);
                break;
            case R.id.btn_expenses:
                fragment = getFragmentFromTransaction(FragmentIncomeAndExpenditure.class, false);
                break;
            case R.id.btn_transfers:
                //fragment = getFragmentFromTransaction(.class);
                break;
        }

        if (fragment != null)
            fragmentManager.beginTransaction().replace(R.id.content_empty_transaction, fragment).commit();
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

    private Fragment getFragmentFromTransaction(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }
}
