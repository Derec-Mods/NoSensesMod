package io.github.derexxd.nosensesmod.network;

import io.github.derexxd.nosensesmod.state.BlindState;
import io.github.derexxd.nosensesmod.state.DeafState;
import io.github.derexxd.nosensesmod.state.MuteState;
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

    public static void broadcastMute(MinecraftServer server, UUID playerId, boolean muted) {
        MuteSyncPayload payload = new MuteSyncPayload(playerId, muted);
        for (ServerPlayerEntity player : PlayerLookup.all(server)) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    public static void sendAllMute(ServerPlayerEntity player) {
        for (UUID playerId : MuteState.snapshot()) {
            ServerPlayNetworking.send(player, new MuteSyncPayload(playerId, true));
        }
    }

    public static void broadcastDeaf(MinecraftServer server, UUID playerId, boolean deafened) {
        DeafSyncPayload payload = new DeafSyncPayload(playerId, deafened);
        for (ServerPlayerEntity player : PlayerLookup.all(server)) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    public static void sendAllDeaf(ServerPlayerEntity player) {
        for (UUID playerId : DeafState.snapshot()) {
            ServerPlayNetworking.send(player, new DeafSyncPayload(playerId, true));
        }
    }
}
