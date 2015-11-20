package com.github.stkent.amplify.helpers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.stkent.amplify.tracking.interfaces.IEvent;
import com.github.stkent.amplify.tracking.interfaces.IEventCheck;
import com.github.stkent.amplify.tracking.interfaces.ISettings;
import com.github.stkent.amplify.tracking.interfaces.ITrackedEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock settings implementation that stores most recent values written,
 * indexed first by IEvent, and then by IEventCheck. This allows for easier
 * verifications during testing.
 */
public class MockSettings<T> implements ISettings<T> {

    private Map<IEvent, Map<IEventCheck, T>> mostRecentValuesWritten = new HashMap<>();

    @Nullable
    public T getEventValue(@NonNull final IEvent event, @NonNull final IEventCheck eventCheck) {
        if (!mostRecentValuesWritten.containsKey(event)) {
            return null;
        }

        final Map<IEventCheck, T> eventMap = mostRecentValuesWritten.get(event);

        if (!eventMap.containsKey(eventCheck)) {
            return null;
        }

        return eventMap.get(eventCheck);
    }

    @Override
    public void writeEventValue(@NonNull final ITrackedEvent trackedEvent, final T value) {
        final IEvent event = trackedEvent.getEvent();

        if (!mostRecentValuesWritten.containsKey(event)) {
            mostRecentValuesWritten.put(event, new HashMap<IEventCheck, T>());
        }

        mostRecentValuesWritten.get(event).put(trackedEvent.getEventCheck(), value);
    }

    @Nullable
    @Override
    public T getEventValue(@NonNull final ITrackedEvent trackedEvent) {
        return getEventValue(trackedEvent.getEvent(), trackedEvent.getEventCheck());
    }

    @Override
    public boolean hasEventValue(@NonNull final ITrackedEvent trackedEvent) {
        return getEventValue(trackedEvent) != null;
    }

}
