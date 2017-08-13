package by.xo.egorp.finance.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WALLET".
*/
public class WalletDao extends AbstractDao<Wallet, Long> {

    public static final String TABLENAME = "WALLET";

    /**
     * Properties of entity Wallet.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property WalletName = new Property(1, String.class, "walletName", false, "WALLET_NAME");
        public final static Property Balance = new Property(2, Float.class, "balance", false, "BALANCE");
        public final static Property CurrencyId = new Property(3, long.class, "currencyId", false, "CURRENCY_ID");
        public final static Property WalletIconId = new Property(4, long.class, "walletIconId", false, "WALLET_ICON_ID");
    }

    private DaoSession daoSession;


    public WalletDao(DaoConfig config) {
        super(config);
    }
    
    public WalletDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WALLET\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"WALLET_NAME\" TEXT," + // 1: walletName
                "\"BALANCE\" REAL," + // 2: balance
                "\"CURRENCY_ID\" INTEGER NOT NULL ," + // 3: currencyId
                "\"WALLET_ICON_ID\" INTEGER NOT NULL );"); // 4: walletIconId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WALLET\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Wallet entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String walletName = entity.getWalletName();
        if (walletName != null) {
            stmt.bindString(2, walletName);
        }
 
        Float balance = entity.getBalance();
        if (balance != null) {
            stmt.bindDouble(3, balance);
        }
        stmt.bindLong(4, entity.getCurrencyId());
        stmt.bindLong(5, entity.getWalletIconId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Wallet entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String walletName = entity.getWalletName();
        if (walletName != null) {
            stmt.bindString(2, walletName);
        }
 
        Float balance = entity.getBalance();
        if (balance != null) {
            stmt.bindDouble(3, balance);
        }
        stmt.bindLong(4, entity.getCurrencyId());
        stmt.bindLong(5, entity.getWalletIconId());
    }

    @Override
    protected final void attachEntity(Wallet entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Wallet readEntity(Cursor cursor, int offset) {
        Wallet entity = new Wallet( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // walletName
            cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2), // balance
            cursor.getLong(offset + 3), // currencyId
            cursor.getLong(offset + 4) // walletIconId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Wallet entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWalletName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setBalance(cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2));
        entity.setCurrencyId(cursor.getLong(offset + 3));
        entity.setWalletIconId(cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Wallet entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Wallet entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Wallet entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCurrencyDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getWalletIconDao().getAllColumns());
            builder.append(" FROM WALLET T");
            builder.append(" LEFT JOIN CURRENCY T0 ON T.\"CURRENCY_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN WALLET_ICON T1 ON T.\"WALLET_ICON_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Wallet loadCurrentDeep(Cursor cursor, boolean lock) {
        Wallet entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Currency currency = loadCurrentOther(daoSession.getCurrencyDao(), cursor, offset);
         if(currency != null) {
            entity.setCurrency(currency);
        }
        offset += daoSession.getCurrencyDao().getAllColumns().length;

        WalletIcon walletIcon = loadCurrentOther(daoSession.getWalletIconDao(), cursor, offset);
         if(walletIcon != null) {
            entity.setWalletIcon(walletIcon);
        }

        return entity;    
    }

    public Wallet loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Wallet> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Wallet> list = new ArrayList<Wallet>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Wallet> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Wallet> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
