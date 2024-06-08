package org.thesalutyt.simplescript.common.time;

import org.thesalutyt.simplescript.common.info.Info;

import java.util.Calendar;
import java.util.Date;

public class Time {
    public synchronized void waitTime(Integer time) {
        try {
            wait(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
    }
    public static String getDate() {
        Date now = Calendar.getInstance().getTime();
        return Info.DEFAULT_DATE_FORMAT.format(now);
    }
}
