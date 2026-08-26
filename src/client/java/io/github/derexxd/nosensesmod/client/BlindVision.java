package io.github.derexxd.nosensesmod.client;

import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import net.minecraft.client.gui.DrawContext;
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
        return new Fog(0.5F, 3.0F, FogShape.SPHERE, 0.0F, 0.0F, 0.0F, 1.0F);
    }

    public static Vector4f color(Vector4f original) {
        if (!ClientBlindState.isLocalBlind() || original == null) {
            return original;
        }
        original.set(0.0F, 0.0F, 0.0F, 1.0F);
        return original;
    }

    public static void renderOverlay(DrawContext context) {
        if (!ClientBlindState.isLocalBlind()) {
            return;
        }
        context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), 0x90000000);
    }
}
