package io.github.derexxd.nosensesmod.state;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class MuteState {
    private static final Set<UUID> MUTED = ConcurrentHashMap.newKeySet();

    private MuteState() {
    }

    public static boolean isMuted(UUID playerId) {
        return MUTED.contains(playerId);
    }

    public static boolean toggle(UUID playerId) {
        if (MUTED.contains(playerId)) {
            MUTED.remove(playerId);
            return false;
        }

        MUTED.add(playerId);
        return true;
    }

    public static void clear(UUID playerId) {
        MUTED.remove(playerId);
    }

    public static Set<UUID> snapshot() {
        return Set.copyOf(MUTED);
    }
}
