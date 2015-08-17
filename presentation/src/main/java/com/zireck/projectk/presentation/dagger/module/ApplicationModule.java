package com.zireck.projectk.presentation.dagger.module;

import android.content.Context;

import com.zireck.projectk.data.executor.JobExecutor;
import com.zireck.projectk.data.repository.FoodDataRepository;
import com.zireck.projectk.domain.executor.PostExecutionThread;
import com.zireck.projectk.domain.executor.ThreadExecutor;
import com.zireck.projectk.domain.repository.FoodRepository;
import com.zireck.projectk.presentation.AndroidApplication;
import com.zireck.projectk.presentation.UIThread;
import com.zireck.projectk.presentation.mapper.FoodModelDataMapper;
import com.zireck.projectk.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;

    public ApplicationModule(AndroidApplication application) {
        mApplication = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    FoodRepository provideFoodRepository(FoodDataRepository foodDataRepository) {
        return foodDataRepository;
    }

    @Provides @Singleton
    FoodModelDataMapper provideFoodModelDataMapper() {
        return new FoodModelDataMapper();
    }
}