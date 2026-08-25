package io.github.derexxd.nosensesmod.mixin.client;

import io.github.derexxd.nosensesmod.client.BlindVision;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.world.ClientWorld;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
    @Inject(method = "applyFog", at = @At("RETURN"), cancellable = true)
    private static void nosensesmod$limitBlindFog(
            Camera camera,
            BackgroundRenderer.FogType fogType,
            Vector4f color,
            float viewDistance,
            boolean thickenFog,
            float tickDelta,
            CallbackInfoReturnable<Fog> cir
    ) {
        Fog fog = BlindVision.fog(cir.getReturnValue());
        if (fog != cir.getReturnValue()) {
            cir.setReturnValue(fog);
        }
    }

    @Inject(method = "getFogColor", at = @At("RETURN"), cancellable = true)
    private static void nosensesmod$blackBlindFog(
            Camera camera,
            float tickDelta,
            ClientWorld world,
            int clampedViewDistance,
            float skyDarkness,
            CallbackInfoReturnable<Vector4f> cir
    ) {
        Vector4f color = BlindVision.color(cir.getReturnValue());
        if (color != cir.getReturnValue()) {
            cir.setReturnValue(color);
        }
    }
}
