package io.github.derexxd.nosensesmod.client.state;

import net.minecraft.client.MinecraftClient;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class ClientBlindState {
    private static final Set<UUID> BLINDED = ConcurrentHashMap.newKeySet();

    private ClientBlindState() {
    }

    public static boolean isBlind(UUID playerId) {
        return BLINDED.contains(playerId);
    }

    public static boolean isLocalBlind() {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.player != null && isBlind(client.player.getUuid());
    }

    public static void set(UUID playerId, boolean blinded) {
        if (blinded) {
            BLINDED.add(playerId);
        } else {
            BLINDED.remove(playerId);
        }
    }

    public static void clear() {
        BLINDED.clear();
    }
}
