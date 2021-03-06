package deltix.containers;

import deltix.containers.interfaces.Consumer;
import deltix.hddatetime.HdDateTime;
import deltix.containers.generated.LongObjPair;
import deltix.containers.interfaces.HdDateTimeProvider;

/**
 * Simple implementation of HdDateTimeProvider interface.
 */
public class ManualHdTimeProvider implements HdDateTimeProvider {
    private ManualTimeProvider timeProvider = new ManualTimeProvider();

    /**
     * Move current time to new time.
     *
     * @param time New time of time provider.
     */
    public void goToTime(HdDateTime time) {
        timeProvider.goToTime(HdDateTime.toUnderlying(time));
    }


    /**
     * Move current time to time of next breakpoint.
     */
    public void goToNextBreakPoint() {
        timeProvider.goToNextBreakPoint();
    }


    /**
     * Get current time from this provider.
     *
     * @return Current time from this provider.
     */
    @Override
    public HdDateTime getCurrentTime() {
        return HdDateTime.fromUnderlying(timeProvider.getCurrentTime());
    }

    /**
     * Add new breakpoint to this provider.
     *
     * @param time     Time of new breakpoint.
     * @param context  Context of new breakpoint.
     * @param action   Action which will be called on new breakpoint.
     * @param priority Priority of new breakpoint (breakpoints with lesser priority with same time called earlier).
     * @return Key of this breakpoint or -1 if we can't add breakpoint(time less than current time).
     */
    @Override
    public long addBreakPoint(HdDateTime time, Object context, Consumer<LongObjPair> action, int priority) {
        return timeProvider.addBreakPoint(HdDateTime.toUnderlying(time), context, action, priority);
    }

    /**
     * Delete breakpoint with key from this provider.
     *
     * @param key Key of breakpoint.
     * @return True if there is such key in TimeProvider.
     */
    @Override
    public boolean deleteBreakPoint(long key) {
        return timeProvider.deleteBreakPoint(key);
    }
}
