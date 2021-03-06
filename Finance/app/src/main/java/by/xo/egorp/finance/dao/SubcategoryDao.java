package by.xo.egorp.finance.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SUBCATEGORY".
*/
public class SubcategoryDao extends AbstractDao<Subcategory, Long> {

    public static final String TABLENAME = "SUBCATEGORY";

    /**
     * Properties of entity Subcategory.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SubcategoryName = new Property(1, String.class, "subcategoryName", false, "SUBCATEGORY_NAME");
        public final static Property CategoryId = new Property(2, long.class, "categoryId", false, "CATEGORY_ID");
    }

    private Query<Subcategory> category_SubcategoriesQuery;

    public SubcategoryDao(DaoConfig config) {
        super(config);
    }
    
    public SubcategoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SUBCATEGORY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SUBCATEGORY_NAME\" TEXT," + // 1: subcategoryName
                "\"CATEGORY_ID\" INTEGER NOT NULL );"); // 2: categoryId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SUBCATEGORY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Subcategory entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String subcategoryName = entity.getSubcategoryName();
        if (subcategoryName != null) {
            stmt.bindString(2, subcategoryName);
        }
        stmt.bindLong(3, entity.getCategoryId());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Subcategory entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String subcategoryName = entity.getSubcategoryName();
        if (subcategoryName != null) {
            stmt.bindString(2, subcategoryName);
        }
        stmt.bindLong(3, entity.getCategoryId());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Subcategory readEntity(Cursor cursor, int offset) {
        Subcategory entity = new Subcategory( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // subcategoryName
            cursor.getLong(offset + 2) // categoryId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Subcategory entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSubcategoryName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCategoryId(cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Subcategory entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Subcategory entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Subcategory entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "subcategories" to-many relationship of Category. */
    public List<Subcategory> _queryCategory_Subcategories(long categoryId) {
        synchronized (this) {
            if (category_SubcategoriesQuery == null) {
                QueryBuilder<Subcategory> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.CategoryId.eq(null));
                category_SubcategoriesQuery = queryBuilder.build();
            }
        }
        Query<Subcategory> query = category_SubcategoriesQuery.forCurrentThread();
        query.setParameter(0, categoryId);
        return query.list();
    }

}
