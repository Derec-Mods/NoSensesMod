package io.github.derexxd.nosensesmod.event;

import io.github.derexxd.nosensesmod.rule.ModGameRules;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public final class SharedDeathHandler {
    private static boolean cascading = false;

    private SharedDeathHandler() {
    }

    public static void register() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (!(entity instanceof ServerPlayerEntity deadPlayer)) {
                return;
            }

            MinecraftServer server = deadPlayer.getServer();
            if (server == null) {
                return;
            }

            if (!server.getGameRules().getBoolean(ModGameRules.SHARED_DEATH)) {
                return;
            }

            if (cascading) {
                return;
            }

            try {
                cascading = true;
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    if (player != deadPlayer && player.isAlive()) {
                        player.damage(player.getServerWorld(), player.getDamageSources().genericKill(), Float.MAX_VALUE);
                    }
                }
            } finally {
                cascading = false;
            }
        });
    }
}
