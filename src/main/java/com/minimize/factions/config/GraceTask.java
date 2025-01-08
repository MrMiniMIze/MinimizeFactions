package com.minimize.factions.config;

import com.minimize.factions.util.Logger;

/**
 * Decrements the Grace period every minute if enabled
 * Author: minimize
 */
public class GraceTask implements Runnable {

    @Override
    public void run() {
        if (!Conf.graceEnabled) return;
        if (Conf.graceMinutesRemaining > 0) {
            Conf.graceMinutesRemaining--;
            if (Conf.graceMinutesRemaining % 10 == 0) {
                Logger.print("[Grace] " + Conf.graceMinutesRemaining + " minutes remain.", Logger.PrefixType.DEFAULT);
            }
        }
    }
}
