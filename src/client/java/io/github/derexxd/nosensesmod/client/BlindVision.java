package io.github.derexxd.nosensesmod.client;

import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FogShape;
import org.joml.Vector4f;

public final class BlindVision {
    public static final float FOG_START = 0.5F;
    public static final float FOG_END = 3.0F;

    private BlindVision() {
    }

    public static Fog fog(Fog original) {
        if (!ClientBlindState.isLocalBlind() || original == null) {
            return original;
        }
        return new Fog(FOG_START, FOG_END, FogShape.SPHERE, 0.0F, 0.0F, 0.0F, 1.0F);
    }

    public static Vector4f color(Vector4f original) {
        if (!ClientBlindState.isLocalBlind() || original == null) {
            return original;
        }
        return new Vector4f(0.0F, 0.0F, 0.0F, 1.0F);
    }
}
