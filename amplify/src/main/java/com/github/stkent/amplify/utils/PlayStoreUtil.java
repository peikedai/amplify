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
package com.github.stkent.amplify.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.content.Intent.ACTION_VIEW;

public final class PlayStoreUtil {

    private static final String ANDROID_MARKET_URI_PREFIX = "market://details?id=";
    private static final String GOOGLE_PLAY_STORE_URI_PREFIX = "https://play.google.com/store/apps/details?id=";

    private PlayStoreUtil() {

    }

    public static void openPlayStoreToRate(
            @NonNull final Activity activity,
            @Nullable String packageName) {

        if (packageName == null) {
            packageName = activity.getPackageName();
        }

        try {
            activity.startActivity(
                    new Intent(
                            ACTION_VIEW,
                            getAndroidMarketUriForPackageName(packageName)
                    )
            );
        } catch (final ActivityNotFoundException e) {
            activity.startActivity(
                    new Intent(
                            ACTION_VIEW,
                            getGooglePlayStoreUriForPackageName(packageName)
                    )
            );
        } finally {
            activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @NonNull
    private static Uri getAndroidMarketUriForPackageName(@NonNull final String packageName) {
        return Uri.parse(ANDROID_MARKET_URI_PREFIX + packageName);
    }

    @NonNull
    private static Uri getGooglePlayStoreUriForPackageName(@NonNull final String packageName) {
        return Uri.parse(GOOGLE_PLAY_STORE_URI_PREFIX + packageName);
    }

}
