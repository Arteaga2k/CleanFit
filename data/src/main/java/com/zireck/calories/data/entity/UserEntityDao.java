package com.zireck.calories.data.entity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table USER_ENTITY.
*/
public class UserEntityDao extends AbstractDao<UserEntity, Long> {

    public static final String TABLENAME = "USER_ENTITY";

    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Gender = new Property(2, int.class, "gender", false, "GENDER");
        public final static Property Birthday = new Property(3, java.util.Date.class, "birthday", false, "BIRTHDAY");
        public final static Property Age = new Property(4, Integer.class, "age", false, "AGE");
        public final static Property Weight = new Property(5, double.class, "weight", false, "WEIGHT");
        public final static Property Height = new Property(6, int.class, "height", false, "HEIGHT");
        public final static Property ActivityFactor = new Property(7, int.class, "activityFactor", false, "ACTIVITY_FACTOR");
        public final static Property Bmr = new Property(8, Double.class, "bmr", false, "BMR");
        public final static Property Goal = new Property(9, int.class, "goal", false, "GOAL");
        public final static Property Maintain = new Property(10, Double.class, "maintain", false, "MAINTAIN");
        public final static Property Burn = new Property(11, Double.class, "burn", false, "BURN");
        public final static Property Gain = new Property(12, Double.class, "gain", false, "GAIN");
    };


    public UserEntityDao(DaoConfig config) {
        super(config);
    }
    
    public UserEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'USER_ENTITY' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'NAME' TEXT," + // 1: name
                "'GENDER' INTEGER NOT NULL ," + // 2: gender
                "'BIRTHDAY' INTEGER NOT NULL ," + // 3: birthday
                "'AGE' INTEGER," + // 4: age
                "'WEIGHT' REAL NOT NULL ," + // 5: weight
                "'HEIGHT' INTEGER NOT NULL ," + // 6: height
                "'ACTIVITY_FACTOR' INTEGER NOT NULL ," + // 7: activityFactor
                "'BMR' REAL," + // 8: bmr
                "'GOAL' INTEGER NOT NULL ," + // 9: goal
                "'MAINTAIN' REAL," + // 10: maintain
                "'BURN' REAL," + // 11: burn
                "'GAIN' REAL);"); // 12: gain
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'USER_ENTITY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, UserEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getGender());
        stmt.bindLong(4, entity.getBirthday().getTime());
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(5, age);
        }
        stmt.bindDouble(6, entity.getWeight());
        stmt.bindLong(7, entity.getHeight());
        stmt.bindLong(8, entity.getActivityFactor());
 
        Double bmr = entity.getBmr();
        if (bmr != null) {
            stmt.bindDouble(9, bmr);
        }
        stmt.bindLong(10, entity.getGoal());
 
        Double maintain = entity.getMaintain();
        if (maintain != null) {
            stmt.bindDouble(11, maintain);
        }
 
        Double burn = entity.getBurn();
        if (burn != null) {
            stmt.bindDouble(12, burn);
        }
 
        Double gain = entity.getGain();
        if (gain != null) {
            stmt.bindDouble(13, gain);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public UserEntity readEntity(Cursor cursor, int offset) {
        UserEntity entity = new UserEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2), // gender
            new java.util.Date(cursor.getLong(offset + 3)), // birthday
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // age
            cursor.getDouble(offset + 5), // weight
            cursor.getInt(offset + 6), // height
            cursor.getInt(offset + 7), // activityFactor
            cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8), // bmr
            cursor.getInt(offset + 9), // goal
            cursor.isNull(offset + 10) ? null : cursor.getDouble(offset + 10), // maintain
            cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11), // burn
            cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12) // gain
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, UserEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setGender(cursor.getInt(offset + 2));
        entity.setBirthday(new java.util.Date(cursor.getLong(offset + 3)));
        entity.setAge(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setWeight(cursor.getDouble(offset + 5));
        entity.setHeight(cursor.getInt(offset + 6));
        entity.setActivityFactor(cursor.getInt(offset + 7));
        entity.setBmr(cursor.isNull(offset + 8) ? null : cursor.getDouble(offset + 8));
        entity.setGoal(cursor.getInt(offset + 9));
        entity.setMaintain(cursor.isNull(offset + 10) ? null : cursor.getDouble(offset + 10));
        entity.setBurn(cursor.isNull(offset + 11) ? null : cursor.getDouble(offset + 11));
        entity.setGain(cursor.isNull(offset + 12) ? null : cursor.getDouble(offset + 12));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(UserEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(UserEntity entity) {
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