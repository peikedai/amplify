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
package com.github.stkent.amplify.tracking.interfaces;

import android.support.annotation.NonNull;

/**
 * An abstract representation of a class that provides the (necessary and nice-to-have) data for
 * receiving critical feedback from the user.
 */
public interface IFeedbackDataProvider {

    /**
     * @return the human-readable name of the current device
     */
    @NonNull
    String getDeviceName();

    /**
     * @return the current version string of the application in which this library is embedded,
     *         formatted for display, or a sensible default if this cannot be determined
     */
    @NonNull
    String getVersionDisplayString();

    /**
     * @return the name of the application in which this library is embedded
     */
    @NonNull
    CharSequence getAppNameString();

    /**
     * @return the version number and API level of the Android operating system running on the
     *         current device, formatted for display
     */
    @NonNull
    String getAndroidOsVersionDisplayString();

}
