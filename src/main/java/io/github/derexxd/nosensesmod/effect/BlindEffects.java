package io.github.derexxd.nosensesmod.effect;

import io.github.derexxd.nosensesmod.state.BlindState;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;

public final class BlindEffects {
    private BlindEffects() {
    }

    public static void register() {
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            if (BlindState.isBlind(newPlayer.getUuid())) {
                apply(newPlayer);
            }
        });
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (server.getTicks() % 20 != 0) {
                return;
            }
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (BlindState.isBlind(player.getUuid()) && !player.hasStatusEffect(StatusEffects.DARKNESS)) {
                    apply(player);
                }
            }
        });
    }

    public static void apply(ServerPlayerEntity player) {
        player.removeStatusEffect(StatusEffects.DARKNESS);
        player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.DARKNESS,
                StatusEffectInstance.INFINITE,
                0,
                false,
                false,
                false
        ));
    }

    public static void remove(ServerPlayerEntity player) {
        player.removeStatusEffect(StatusEffects.DARKNESS);
    }
}
