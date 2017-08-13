package by.xo.egorp.finance;


import android.app.Application;

import org.greenrobot.greendao.database.Database;

import by.xo.egorp.finance.dao.DaoMaster;
import by.xo.egorp.finance.dao.DaoSession;

public class AppController extends Application {

    //With encryption, there is complexity so for now "False".
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper
                (this, ENCRYPTED ? "XO-finance-db-encrypted" : "XO-finance-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("financial-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
