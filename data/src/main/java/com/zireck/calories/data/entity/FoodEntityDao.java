package com.zireck.calories.data.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table FOOD_ENTITY.
*/
public class FoodEntityDao extends AbstractDao<FoodEntity, Long> {

    public static final String TABLENAME = "FOOD_ENTITY";

    /**
     * Properties of entity FoodEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Brand = new Property(2, String.class, "brand", false, "BRAND");
        public final static Property IsDrink = new Property(3, boolean.class, "isDrink", false, "IS_DRINK");
        public final static Property Calories = new Property(4, double.class, "calories", false, "CALORIES");
        public final static Property Fats = new Property(5, double.class, "fats", false, "FATS");
        public final static Property Carbohydrates = new Property(6, double.class, "carbohydrates", false, "CARBOHYDRATES");
        public final static Property Proteins = new Property(7, double.class, "proteins", false, "PROTEINS");
        public final static Property Picture = new Property(8, String.class, "picture", false, "PICTURE");
    };


    public FoodEntityDao(DaoConfig config) {
        super(config);
    }
    
    public FoodEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'FOOD_ENTITY' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'NAME' TEXT NOT NULL ," + // 1: name
                "'BRAND' TEXT NOT NULL ," + // 2: brand
                "'IS_DRINK' INTEGER NOT NULL ," + // 3: isDrink
                "'CALORIES' REAL NOT NULL ," + // 4: calories
                "'FATS' REAL NOT NULL ," + // 5: fats
                "'CARBOHYDRATES' REAL NOT NULL ," + // 6: carbohydrates
                "'PROTEINS' REAL NOT NULL ," + // 7: proteins
                "'PICTURE' TEXT);"); // 8: picture
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FOOD_ENTITY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, FoodEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getBrand());
        stmt.bindLong(4, entity.getIsDrink() ? 1l: 0l);
        stmt.bindDouble(5, entity.getCalories());
        stmt.bindDouble(6, entity.getFats());
        stmt.bindDouble(7, entity.getCarbohydrates());
        stmt.bindDouble(8, entity.getProteins());
 
        String picture = entity.getPicture();
        if (picture != null) {
            stmt.bindString(9, picture);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public FoodEntity readEntity(Cursor cursor, int offset) {
        FoodEntity entity = new FoodEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // brand
            cursor.getShort(offset + 3) != 0, // isDrink
            cursor.getDouble(offset + 4), // calories
            cursor.getDouble(offset + 5), // fats
            cursor.getDouble(offset + 6), // carbohydrates
            cursor.getDouble(offset + 7), // proteins
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // picture
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, FoodEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setBrand(cursor.getString(offset + 2));
        entity.setIsDrink(cursor.getShort(offset + 3) != 0);
        entity.setCalories(cursor.getDouble(offset + 4));
        entity.setFats(cursor.getDouble(offset + 5));
        entity.setCarbohydrates(cursor.getDouble(offset + 6));
        entity.setProteins(cursor.getDouble(offset + 7));
        entity.setPicture(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(FoodEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(FoodEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
