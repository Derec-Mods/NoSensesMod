package io.github.derexxd.nosensesmod.client;

import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import org.joml.Vector4f;

public final class BlindVision {
    private BlindVision() {
    }

    public static Fog fog(Fog original) {
        if (!ClientBlindState.isLocalBlind() || original == null) {
            return original;
        }
        float open = BlindPulse.openness(MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false));
        float start = 0.2F + 0.3F * open;
        float end = 1.0F + 2.0F * open;
        return new Fog(start, end, FogShape.SPHERE, 0.0F, 0.0F, 0.0F, 1.0F);
    }

    public static Vector4f color(Vector4f original) {
        if (!ClientBlindState.isLocalBlind() || original == null) {
            return original;
        }
        original.set(0.0F, 0.0F, 0.0F, 1.0F);
        return original;
    }
}
