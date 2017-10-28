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
 * DAO for table "FINANCE_TRANSACTION".
*/
public class FinanceTransactionDao extends AbstractDao<FinanceTransaction, Long> {

    public static final String TABLENAME = "FINANCE_TRANSACTION";

    /**
     * Properties of entity FinanceTransaction.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property TransactionType = new Property(1, Boolean.class, "transactionType", false, "TRANSACTION_TYPE");
        public final static Property Amount = new Property(2, Float.class, "amount", false, "AMOUNT");
        public final static Property Data = new Property(3, java.util.Date.class, "data", false, "DATA");
        public final static Property Description = new Property(4, String.class, "description", false, "DESCRIPTION");
        public final static Property Photo = new Property(5, Byte.class, "photo", false, "PHOTO");
        public final static Property WalletId = new Property(6, long.class, "walletId", false, "WALLET_ID");
        public final static Property CategoryId = new Property(7, long.class, "categoryId", false, "CATEGORY_ID");
        public final static Property SubcategoryId = new Property(8, Long.class, "subcategoryId", false, "SUBCATEGORY_ID");
    }

    private DaoSession daoSession;


    public FinanceTransactionDao(DaoConfig config) {
        super(config);
    }
    
    public FinanceTransactionDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"FINANCE_TRANSACTION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TRANSACTION_TYPE\" INTEGER," + // 1: transactionType
                "\"AMOUNT\" REAL," + // 2: amount
                "\"DATA\" INTEGER," + // 3: data
                "\"DESCRIPTION\" TEXT," + // 4: description
                "\"PHOTO\" INTEGER," + // 5: photo
                "\"WALLET_ID\" INTEGER NOT NULL ," + // 6: walletId
                "\"CATEGORY_ID\" INTEGER NOT NULL ," + // 7: categoryId
                "\"SUBCATEGORY_ID\" INTEGER);"); // 8: subcategoryId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"FINANCE_TRANSACTION\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, FinanceTransaction entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Boolean transactionType = entity.getTransactionType();
        if (transactionType != null) {
            stmt.bindLong(2, transactionType ? 1L: 0L);
        }
 
        Float amount = entity.getAmount();
        if (amount != null) {
            stmt.bindDouble(3, amount);
        }
 
        java.util.Date data = entity.getData();
        if (data != null) {
            stmt.bindLong(4, data.getTime());
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
 
        Byte photo = entity.getPhoto();
        if (photo != null) {
            stmt.bindLong(6, photo);
        }
        stmt.bindLong(7, entity.getWalletId());
        stmt.bindLong(8, entity.getCategoryId());
 
        Long subcategoryId = entity.getSubcategoryId();
        if (subcategoryId != null) {
            stmt.bindLong(9, subcategoryId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, FinanceTransaction entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Boolean transactionType = entity.getTransactionType();
        if (transactionType != null) {
            stmt.bindLong(2, transactionType ? 1L: 0L);
        }
 
        Float amount = entity.getAmount();
        if (amount != null) {
            stmt.bindDouble(3, amount);
        }
 
        java.util.Date data = entity.getData();
        if (data != null) {
            stmt.bindLong(4, data.getTime());
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
 
        Byte photo = entity.getPhoto();
        if (photo != null) {
            stmt.bindLong(6, photo);
        }
        stmt.bindLong(7, entity.getWalletId());
        stmt.bindLong(8, entity.getCategoryId());
 
        Long subcategoryId = entity.getSubcategoryId();
        if (subcategoryId != null) {
            stmt.bindLong(9, subcategoryId);
        }
    }

    @Override
    protected final void attachEntity(FinanceTransaction entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public FinanceTransaction readEntity(Cursor cursor, int offset) {
        FinanceTransaction entity = new FinanceTransaction( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0, // transactionType
            cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2), // amount
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)), // data
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // description
            cursor.isNull(offset + 5) ? null : (byte) cursor.getShort(offset + 5), // photo
            cursor.getLong(offset + 6), // walletId
            cursor.getLong(offset + 7), // categoryId
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8) // subcategoryId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, FinanceTransaction entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTransactionType(cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0);
        entity.setAmount(cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2));
        entity.setData(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhoto(cursor.isNull(offset + 5) ? null : (byte) cursor.getShort(offset + 5));
        entity.setWalletId(cursor.getLong(offset + 6));
        entity.setCategoryId(cursor.getLong(offset + 7));
        entity.setSubcategoryId(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(FinanceTransaction entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(FinanceTransaction entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(FinanceTransaction entity) {
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
            SqlUtils.appendColumns(builder, "T0", daoSession.getWalletDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getCategoryDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getSubcategoryDao().getAllColumns());
            builder.append(" FROM FINANCE_TRANSACTION T");
            builder.append(" LEFT JOIN WALLET T0 ON T.\"WALLET_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN CATEGORY T1 ON T.\"CATEGORY_ID\"=T1.\"_id\"");
            builder.append(" LEFT JOIN SUBCATEGORY T2 ON T.\"SUBCATEGORY_ID\"=T2.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected FinanceTransaction loadCurrentDeep(Cursor cursor, boolean lock) {
        FinanceTransaction entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Wallet wallet = loadCurrentOther(daoSession.getWalletDao(), cursor, offset);
         if(wallet != null) {
            entity.setWallet(wallet);
        }
        offset += daoSession.getWalletDao().getAllColumns().length;

        Category category = loadCurrentOther(daoSession.getCategoryDao(), cursor, offset);
         if(category != null) {
            entity.setCategory(category);
        }
        offset += daoSession.getCategoryDao().getAllColumns().length;

        Subcategory subcategory = loadCurrentOther(daoSession.getSubcategoryDao(), cursor, offset);
        entity.setSubcategory(subcategory);

        return entity;    
    }

    public FinanceTransaction loadDeep(Long key) {
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
    public List<FinanceTransaction> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<FinanceTransaction> list = new ArrayList<FinanceTransaction>(count);
        
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
    
    protected List<FinanceTransaction> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<FinanceTransaction> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
