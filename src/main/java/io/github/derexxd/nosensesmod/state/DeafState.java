package io.github.derexxd.nosensesmod.state;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class DeafState {
    private static final Set<UUID> DEAFENED = ConcurrentHashMap.newKeySet();

    private DeafState() {
    }

    public static boolean isDeaf(UUID playerId) {
        return DEAFENED.contains(playerId);
    }

    public static boolean toggle(UUID playerId) {
        if (DEAFENED.contains(playerId)) {
            DEAFENED.remove(playerId);
            return false;
        }

        DEAFENED.add(playerId);
        return true;
    }

    public static void clear(UUID playerId) {
        DEAFENED.remove(playerId);
    }

    public static Set<UUID> snapshot() {
        return Set.copyOf(DEAFENED);
    }
}
