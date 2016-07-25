package com.example.jimmy.cornalarmclock.util;


import de.greenrobot.event.EventBus;

public class AndroidEventBus {

    private EventBus bus;

    public AndroidEventBus() {
        this(false);
    }

    private AndroidEventBus(boolean isDefault) {
        if (isDefault) {
            bus = EventBus.getDefault();
        } else {
            bus = new EventBus();
        }
    }

    public static AndroidEventBus getDefault() {
        return new AndroidEventBus(true);
    }

    public void register(Object subscriber) {
        if (!isRegistered(subscriber)) {
            bus.register(subscriber);
        }
    }

    public void register(Object subscriber, int priority) {
        if (!isRegistered(subscriber)) {
            bus.register(subscriber, priority);
        }
    }

    public void registerSticky(Object subscriber) {
        if (!isRegistered(subscriber)) {
            bus.registerSticky(subscriber);
        }
    }

    public void registerSticky(Object subscriber, int priority) {
        if (!isRegistered(subscriber)) {
            bus.registerSticky(subscriber, priority);
        }
    }

    public void unregister(Object subscriber) {
        bus.unregister(subscriber);

    }

    public synchronized boolean isRegistered(Object subscriber) {
        return bus.isRegistered(subscriber);
    }

    public void post(Object event) {
        bus.post(event);
    }

    public void cancelEventDelivery(Object event) {
        bus.cancelEventDelivery(event);
    }

    public void postSticky(Object event) {
        bus.postSticky(event);
    }

    public <T> T getStickyEvent(Class<T> eventType) {
        return bus.getStickyEvent(eventType);
    }

    public <T> T removeStickyEvent(Class<T> eventType) {
        return bus.removeStickyEvent(eventType);
    }

    public boolean removeStickyEvent(Object event) {
        return bus.removeStickyEvent(event);
    }

    public void removeAllStickyEvents() {
        bus.removeAllStickyEvents();
    }

    public boolean hasSubscriberForEvent(Class<?> eventClass) {
        return bus.hasSubscriberForEvent(eventClass);
    }
}