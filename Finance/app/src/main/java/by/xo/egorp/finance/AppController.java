package by.xo.egorp.finance;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import by.xo.egorp.finance.dao.DaoMaster;
import by.xo.egorp.finance.dao.DaoSession;

public class AppController extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "XO-finance-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        BaseValuesForDB baseValuesForDB = new BaseValuesForDB(this);
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
