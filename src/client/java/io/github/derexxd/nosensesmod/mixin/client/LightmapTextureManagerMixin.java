package io.github.derexxd.nosensesmod.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import net.minecraft.client.render.LightmapTextureManager;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightmapTextureManager.class)
public abstract class LightmapTextureManagerMixin {
    @ModifyExpressionValue(
            method = "update",
            at = @At(value = "NEW", target = "(FFF)Lorg/joml/Vector3f;", ordinal = 0)
    )
    private Vector3f nosensesmod$stripBlindSkyBlue(Vector3f skyColor) {
        if (ClientBlindState.isLocalBlind()) {
            return new Vector3f();
        }
        return skyColor;
    }
}
