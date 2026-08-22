package io.github.derexxd.nosensesmod.client.state;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ClientMuteState {
    private static final Set<UUID> MUTED = ConcurrentHashMap.newKeySet();

    private ClientMuteState() {
    }

    public static boolean isMuted(UUID playerId) {
        return MUTED.contains(playerId);
    }

    public static void set(UUID playerId, boolean muted) {
        if (muted) {
            MUTED.add(playerId);
        } else {
            MUTED.remove(playerId);
        }
    }

    public static void clear() {
        MUTED.clear();
    }
}
