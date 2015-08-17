package com.zireck.projectk.presentation.interactor;

import com.zireck.projectk.presentation.listener.OnAddFoodInteractorFinishedListener;
import com.zireck.projectk.presentation.model.FoodModel;

/**
 * Created by Zireck on 03/08/2015.
 */
public interface AddFoodInteractor {
    public void addFood(OnAddFoodInteractorFinishedListener listener, FoodModel food);
}