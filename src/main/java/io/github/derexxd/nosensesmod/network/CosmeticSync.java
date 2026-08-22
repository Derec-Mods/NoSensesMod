package io.github.derexxd.nosensesmod.network;

import io.github.derexxd.nosensesmod.state.BlindState;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.UUID;

public final class CosmeticSync {
    private CosmeticSync() {
    }

    public static void broadcastBlind(MinecraftServer server, UUID playerId, boolean blinded) {
        BlindSyncPayload payload = new BlindSyncPayload(playerId, blinded);
        for (ServerPlayerEntity player : PlayerLookup.all(server)) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    public static void sendAllBlind(ServerPlayerEntity player) {
        for (UUID playerId : BlindState.snapshot()) {
            ServerPlayNetworking.send(player, new BlindSyncPayload(playerId, true));
        }
    }
}
