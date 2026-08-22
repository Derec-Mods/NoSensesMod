package io.github.derexxd.nosensesmod.state;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class BlindState {
    private static final Set<UUID> BLINDED = ConcurrentHashMap.newKeySet();

    private BlindState() {
    }

    public static boolean isBlind(UUID playerId) {
        return BLINDED.contains(playerId);
    }

    public static boolean toggle(UUID playerId) {
        if (BLINDED.contains(playerId)) {
            BLINDED.remove(playerId);
            return false;
        }

        BLINDED.add(playerId);
        return true;
    }

    public static void clear(UUID playerId) {
        BLINDED.remove(playerId);
    }

    public static Set<UUID> snapshot() {
        return Set.copyOf(BLINDED);
    }
}
