package com.zireck.projectk.domain.executor;

import rx.Scheduler;

/**
 * Created by Zireck on 13/08/2015.
 */
public interface PostExecutionThread {
    Scheduler getScheduler();
}
