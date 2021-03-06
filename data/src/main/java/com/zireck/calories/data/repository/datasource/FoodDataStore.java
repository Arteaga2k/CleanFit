package com.zireck.calories.data.repository.datasource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zireck.calories.data.entity.FoodEntity;
import com.zireck.calories.data.entity.FoodEntityDao;
import com.zireck.calories.data.util.GreenDaoUtils;
import com.zireck.calories.data.entity.DaoMaster;
import com.zireck.calories.data.entity.DaoSession;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Zireck on 11/08/2015.
 */
public class FoodDataStore {

    @Inject Context mContext;

    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private SQLiteDatabase mDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private FoodEntityDao mFoodEntityDao;

    @Inject
    public FoodDataStore() {

    }

    private DaoSession initGreenDao() {
        mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext, GreenDaoUtils.DATABASE_NAME, null);
        mDatabase = mDevOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDatabase);
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    private void closeGreenDao() {
        //mDatabase.close();
    }

    private FoodEntityDao getFoodEntityDao() {
        mFoodEntityDao = initGreenDao().getFoodEntityDao();
        return mFoodEntityDao;
    }

    public Observable<FoodEntity> foodEntityDetails(final long foodId) {
        return Observable.create(new Observable.OnSubscribe<FoodEntity>() {
            @Override
            public void call(Subscriber<? super FoodEntity> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                FoodEntity foodEntity = foodEntityDao.queryBuilder().where(FoodEntityDao.Properties.Id.eq(foodId)).unique();

                if (foodEntity != null) {
                    subscriber.onNext(foodEntity);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable());
                }

                closeGreenDao();
            }
        });
    }

    public Observable<List<FoodEntity>> foodEntityList() {
        return Observable.create(new Observable.OnSubscribe<List<FoodEntity>>() {
            @Override
            public void call(Subscriber<? super List<FoodEntity>> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                List<FoodEntity> foodEntities;
                foodEntities = foodEntityDao.queryBuilder().orderAsc(FoodEntityDao.Properties.Name).list();

                if (foodEntities != null) {
                    subscriber.onNext(foodEntities);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable());
                }

                closeGreenDao();
            }
        });
    }

    public Observable<List<FoodEntity>> foodList() {
        return Observable.create(new Observable.OnSubscribe<List<FoodEntity>>() {
            @Override
            public void call(Subscriber<? super List<FoodEntity>> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                List<FoodEntity> foodEntities;
                foodEntities = foodEntityDao.queryBuilder().where(FoodEntityDao.Properties.IsDrink.eq(false)).orderAsc(FoodEntityDao.Properties.Name).list();

                if (foodEntities != null) {
                    subscriber.onNext(foodEntities);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable());
                }

                closeGreenDao();
            }
        });
    }

    public Observable<List<FoodEntity>> drinkList() {
        return Observable.create(new Observable.OnSubscribe<List<FoodEntity>>() {
            @Override
            public void call(Subscriber<? super List<FoodEntity>> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                List<FoodEntity> foodEntities;
                foodEntities = foodEntityDao.queryBuilder().where(FoodEntityDao.Properties.IsDrink.eq(true)).orderAsc(FoodEntityDao.Properties.Name).list();

                if (foodEntities != null) {
                    subscriber.onNext(foodEntities);
                    subscriber.onCompleted();
                } else {
                    subscriber.onError(new Throwable());
                }

                closeGreenDao();
            }
        });
    }

    public Observable<Void> addFood(final FoodEntity food) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                food.setId(null);
                long result = foodEntityDao.insert(food);

                if (result == -1) {
                    subscriber.onError(new Throwable());
                } else {
                    subscriber.onCompleted();
                }

                closeGreenDao();
            }
        });
    }

    public Observable<Void> editFood(final FoodEntity food) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                foodEntityDao.update(food);

                subscriber.onCompleted();

                closeGreenDao();
            }
        });
    }

    public Observable<Void> deleteFood(final FoodEntity food) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                foodEntityDao.deleteByKey(food.getId());

                subscriber.onCompleted();

                closeGreenDao();
            }
        });
    }

    public Observable<Boolean> isCatalogEmpty() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                FoodEntityDao foodEntityDao = getFoodEntityDao();
                List<FoodEntity> foodCatalog = foodEntityDao.loadAll();

                if (foodCatalog == null || foodCatalog.size() <= 0) {
                    subscriber.onNext(true);
                } else {
                    subscriber.onNext(false);
                }

                subscriber.onCompleted();
            }
        });
    }
}
