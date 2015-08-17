package com.zireck.projectk.data.repository;

import com.zireck.projectk.data.entity.FoodEntity;
import com.zireck.projectk.data.entity.mapper.FoodEntityDataMapper;
import com.zireck.projectk.data.repository.datasource.FoodDataStore;
import com.zireck.projectk.domain.Food;
import com.zireck.projectk.domain.repository.FoodRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * {@link FoodRepository} for retrieving food data.
 */
public class FoodDataRepository implements FoodRepository {

    private FoodDataStore mFoodDataStore;
    private FoodEntityDataMapper mFoodEntityDataMapper;

    @Inject
    public FoodDataRepository(FoodDataStore foodDataStore,
                              FoodEntityDataMapper foodEntityDataMapper) {
        mFoodDataStore = foodDataStore;
        mFoodEntityDataMapper = foodEntityDataMapper;
    }

    @Override
    public Observable<Food> food(long foodId) {
        return mFoodDataStore.foodEntityDetails(foodId).map(new Func1<FoodEntity, Food>() {
            @Override
            public Food call(FoodEntity foodEntity) {
                return mFoodEntityDataMapper.transform(foodEntity);
            }
        });
    }

    @Override
    public Observable<List<Food>> allFood() {
        return mFoodDataStore.foodEntityList().map(new Func1<List<FoodEntity>, List<Food>>() {
            @Override
            public List<Food> call(List<FoodEntity> foodEntities) {
                return mFoodEntityDataMapper.transform(foodEntities);
            }
        });
    }

    @Override
    public Observable<List<Food>> foods() {
        return mFoodDataStore.foodList().map(new Func1<List<FoodEntity>, List<Food>>() {
            @Override
            public List<Food> call(List<FoodEntity> foodEntities) {
                return mFoodEntityDataMapper.transform(foodEntities);
            }
        });
    }

    @Override
    public Observable<List<Food>> drinks() {
        return mFoodDataStore.drinkList().map(new Func1<List<FoodEntity>, List<Food>>() {
            @Override
            public List<Food> call(List<FoodEntity> foodEntities) {
                return mFoodEntityDataMapper.transform(foodEntities);
            }
        });
    }

    @Override
    public Observable<Void> addFood(Food food) {
        return mFoodDataStore.addFood(mFoodEntityDataMapper.transformInverse(food));
    }

    @Override
    public Observable<Void> deleteFood(final Food food) {
        return mFoodDataStore.deleteFood(mFoodEntityDataMapper.transformInverse(food));
    }
}