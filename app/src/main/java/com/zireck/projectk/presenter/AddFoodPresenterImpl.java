package com.zireck.projectk.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import com.zireck.projectk.helper.PictureHelper;
import com.zireck.projectk.model.Food;
import com.zireck.projectk.model.FoodDao;
import com.zireck.projectk.model.GreenDaoHelper;
import com.zireck.projectk.util.MathUtils;
import com.zireck.projectk.view.AddFoodView;

/**
 * Created by Zireck on 24/07/2015.
 */
public class AddFoodPresenterImpl implements AddFoodPresenter {

    private AddFoodView mView;
    private PictureHelper mPictureHelper;

    public AddFoodPresenterImpl(Context context, AddFoodView view) {
        mPictureHelper = new PictureHelper(context);
        mView = view;
    }

    @Override
    public void validateData(String name, String brand, boolean isDrink, String calories, String fats, String carboydrates, String proteins, String pictureFileName) {
        boolean error = false;

        if (TextUtils.isEmpty(name)) {
            mView.setNameError();
            error = true;
        }

        if (TextUtils.isEmpty(brand)) {
            mView.setBrandError();
            error = true;
        }

        if (TextUtils.isEmpty(calories) || !MathUtils.isDouble(calories)) {
            mView.setCaloriesError();
            error = true;
        }

        if (TextUtils.isEmpty(fats) || !MathUtils.isDouble(fats)) {
            mView.setFatsError();
            error = true;
        }

        if (TextUtils.isEmpty(carboydrates) || !MathUtils.isDouble(carboydrates)) {
            mView.setCarbohydratesError();
            error = true;
        }

        if (TextUtils.isEmpty(proteins) || !MathUtils.isDouble(proteins)) {
            mView.setProteinsError();
            error = true;
        }

        if (!error) {
            Food food = new Food();
            food.setName(name);
            food.setBrand(brand);
            food.setIsDrink(isDrink);
            food.setCalories(Double.valueOf(calories));
            food.setFats(Double.valueOf(fats));
            food.setCarbohydrates(Double.valueOf(carboydrates));
            food.setProteins(Double.valueOf(proteins));
            food.setPicture(pictureFileName);

            GreenDaoHelper greenDaoHelper = new GreenDaoHelper();
            FoodDao foodDao = greenDaoHelper.getFoodDao();
            foodDao.insert(food);

            mView.foodSuccessfullyAdded();
        }
    }

    @Override
    public void isDrink(boolean isDrink) {
        if (isDrink) {
            mView.setMl();
        } else {
            mView.setGr();
        }
    }

    @Override
    public void startCamera(Context context) {
        String fileName = mPictureHelper.generateFileName();
        mView.setPictureNewName(fileName);

        Intent intent = mPictureHelper.getIntentForCameraLaunch(fileName);
        mView.startIntentForCameraLaunch(intent, mPictureHelper.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void receivePicture() {
        if (!TextUtils.isEmpty(mView.getPictureCurrentName())) {
            deleteCurrentPicture();
        }

        System.out.println("k9d3 receiving picture with (new)name = " + mView.getPictureNewName());

        Uri pictureUri = mPictureHelper.getPhotoFileUri(mPictureHelper.getFolderName(), mView.getPictureNewName());
        //Bitmap picture = mPictureHelper.getPictureBitmap(pictureUri);
        Bitmap picture = mPictureHelper.getSampledPictureBitmap(pictureUri, mView.getPictureWidth(), mView.getPictureHeight());
        mView.setPicture(picture);

        mView.setPictureCurrentName(mView.getPictureNewName());
        mView.setPictureNewName("");

        mView.showDeletePictureButton();
    }

    @Override
    public void doNotReceivePicture() {
        mView.setPictureNewName("");

        if (!TextUtils.isEmpty(mView.getPictureCurrentName())) {
            mView.showDeletePictureButton();
        } else {
            mView.hideDeletePictureButton();
        }
    }

    @Override
    public void deleteCurrentPicture() {
        mPictureHelper.deletePicture(mView.getPictureCurrentName());
        mView.setPictureCurrentName("");
        mView.deletePicture();
        mView.hideDeletePictureButton();
    }

}
