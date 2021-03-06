package com.zireck.calories.presentation.navigation;

import android.app.Activity;
import android.content.Intent;

import com.zireck.calories.presentation.view.activity.AddMealActivity;
import com.zireck.calories.presentation.model.FoodModel;
import com.zireck.calories.presentation.view.activity.AddFoodActivity;
import com.zireck.calories.presentation.view.activity.EditFoodActivity;
import com.zireck.calories.presentation.view.activity.FoodDetailActivity;
import com.zireck.calories.presentation.view.activity.SettingsActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class created to handle all the navigation between activities. This class knows how to open
 * every activity in the application and provides to the client code different methods to start
 * activities with the information needed.
 *
 * Created by Zireck on 29/07/2015.
 */
@Singleton
public class Navigator {

    public static final int ADD_FOOD_REQUEST = 1;
    public static final int EDIT_FOOD_REQUEST = 2;
    public static final int DELETE_FOOD_REQUEST = 3;
    public static final int ADD_MEAL_REQUEST = 4;

    @Inject
    public Navigator() {

    }

    /**
     * Open AddFoodActivity
     */
    public void openAddFoodActivity(Activity activity) {
        Intent intent = AddFoodActivity.getLaunchIntent(activity);
        startActivityForResult(activity, intent, ADD_FOOD_REQUEST);
    }

    /**
     * Open FoodDetailActivity using a FoodModel object.
     */
    public void openFoodDetailActivity(Activity activity, final FoodModel food) {
        Intent intent = FoodDetailActivity.getLaunchIntent(activity, food);
        startActivityForResult(activity, intent, DELETE_FOOD_REQUEST);
    }

    /**
     * Open EditFoodActivity
     * @param food
     */
    public void openEditFoodActivity(Activity activity, final FoodModel food) {
        Intent intent = EditFoodActivity.getLaunchIntent(activity, food);
        startActivityForResult(activity, intent, EDIT_FOOD_REQUEST);
    }

    /**
     * Open AddMealActivity
     */
    public void openAddMealActivity(Activity activity) {
        Intent intent = AddMealActivity.getLaunchIntent(activity);
        startActivityForResult(activity, intent, ADD_MEAL_REQUEST);
    }

    /**
     * Open SettingsActivity
     */
    public void openSettingsActivity(Activity activity) {
        Intent intent = SettingsActivity.getLaunchIntent(activity);
        startActivity(activity, intent);
    }

    private void startActivity(Activity activity, Intent intent) {
        checkValidActivity(activity);
        activity.startActivity(intent);
    }

    private void startActivityForResult(Activity activity, Intent intent, final int request) {
        checkValidActivity(activity);
        activity.startActivityForResult(intent, request);
    }

    private void checkValidActivity(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null.");
        }
    }
}
