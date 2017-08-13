package by.xo.egorp.finance.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CURRENCY".
*/
public class CurrencyDao extends AbstractDao<Currency, Long> {

    public static final String TABLENAME = "CURRENCY";

    /**
     * Properties of entity Currency.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CurrencyName = new Property(1, String.class, "currencyName", false, "CURRENCY_NAME");
        public final static Property CurrencyCode = new Property(2, String.class, "currencyCode", false, "CURRENCY_CODE");
    }


    public CurrencyDao(DaoConfig config) {
        super(config);
    }
    
    public CurrencyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CURRENCY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CURRENCY_NAME\" TEXT," + // 1: currencyName
                "\"CURRENCY_CODE\" TEXT);"); // 2: currencyCode
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CURRENCY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Currency entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String currencyName = entity.getCurrencyName();
        if (currencyName != null) {
            stmt.bindString(2, currencyName);
        }
 
        String currencyCode = entity.getCurrencyCode();
        if (currencyCode != null) {
            stmt.bindString(3, currencyCode);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Currency entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String currencyName = entity.getCurrencyName();
        if (currencyName != null) {
            stmt.bindString(2, currencyName);
        }
 
        String currencyCode = entity.getCurrencyCode();
        if (currencyCode != null) {
            stmt.bindString(3, currencyCode);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Currency readEntity(Cursor cursor, int offset) {
        Currency entity = new Currency( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // currencyName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // currencyCode
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Currency entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCurrencyName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCurrencyCode(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Currency entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Currency entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Currency entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}