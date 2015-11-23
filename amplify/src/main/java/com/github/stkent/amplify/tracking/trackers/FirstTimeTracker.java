/**
 * Copyright 2015 Stuart Kent
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.github.stkent.amplify.tracking.trackers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.github.stkent.amplify.ILogger;
import com.github.stkent.amplify.tracking.ApplicationInfoProvider;
import com.github.stkent.amplify.tracking.ClockUtil;
import com.github.stkent.amplify.tracking.Settings;
import com.github.stkent.amplify.tracking.interfaces.IApplicationInfoProvider;
import com.github.stkent.amplify.tracking.interfaces.ISettings;

public class FirstTimeTracker extends EventTracker<Long> {

    public FirstTimeTracker(@NonNull final ILogger logger, @NonNull final Context applicationContext) {
        this(logger, new Settings<Long>(applicationContext, logger), new ApplicationInfoProvider(applicationContext));
    }

    @VisibleForTesting
    protected FirstTimeTracker(
            @NonNull final ILogger logger,
            @NonNull final ISettings<Long> settings,
            @NonNull final IApplicationInfoProvider applicationInfoProvider) {
        super(logger, settings, applicationInfoProvider);
    }

    @NonNull
    @Override
    public Long computeUpdatedTrackingValue(@NonNull final Long cachedValue) {
        if (cachedValue == Long.MAX_VALUE) {
            final Long currentTime = ClockUtil.getCurrentTimeMillis();
            getLogger().d("FirstTimePredicate updating event value from: " + cachedValue + ", to: " + currentTime);
            return Math.min(cachedValue, currentTime);
        }

        return cachedValue;
    }

    @NonNull
    @Override
    public Long defaultTrackingValue() {
        return Long.MAX_VALUE;
    }
}
