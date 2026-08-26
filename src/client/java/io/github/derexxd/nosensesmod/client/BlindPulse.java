package io.github.derexxd.nosensesmod.client;

import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;

public final class BlindPulse {
    private static final float CYCLE = (float) Math.PI * 0.025F;
    private static boolean heartbeatPlayed;

    private BlindPulse() {
    }

    public static float openness(float tickDelta) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) {
            return 1.0F;
        }
        return 0.5F * ((float) Math.cos((player.age + tickDelta) * CYCLE) + 1.0F);
    }

    public static void tick(MinecraftClient client) {
        if (client.player == null || !ClientBlindState.isLocalBlind()) {
            heartbeatPlayed = false;
            return;
        }
        float wave = (float) Math.cos(client.player.age * CYCLE);
        if (wave < -0.85F) {
            if (!heartbeatPlayed) {
                client.player.playSound(SoundEvents.ENTITY_WARDEN_HEARTBEAT, 1.0F, 0.8F);
                heartbeatPlayed = true;
            }
        } else if (wave > 0.0F) {
            heartbeatPlayed = false;
        }
    }

    public static void renderOverlay(DrawContext context) {
        if (!ClientBlindState.isLocalBlind()) {
            return;
        }
        float closed = 1.0F - openness(MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false));
        int alpha = (int) (90.0F + closed * 140.0F);
        context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), alpha << 24);
    }
}
