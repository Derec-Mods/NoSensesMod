package io.github.derexxd.nosensesmod.client.state;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ClientDeafState {
    private static final Set<UUID> DEAFENED = ConcurrentHashMap.newKeySet();

    private ClientDeafState() {
    }

    public static boolean isDeaf(UUID playerId) {
        return DEAFENED.contains(playerId);
    }

    public static void set(UUID playerId, boolean deafened) {
        if (deafened) {
            DEAFENED.add(playerId);
        } else {
            DEAFENED.remove(playerId);
        }
    }

    public static void clear() {
        DEAFENED.clear();
    }
}
