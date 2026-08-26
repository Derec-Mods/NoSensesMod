package io.github.derexxd.nosensesmod.mixin.client;

import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Fog;
import net.minecraft.client.render.FrameGraphBuilder;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Inject(method = "renderSky", at = @At("HEAD"), cancellable = true)
    private void nosensesmod$hideBlindSky(FrameGraphBuilder frameGraphBuilder, Camera camera, float tickDelta, Fog fog, CallbackInfo ci) {
        if (ClientBlindState.isLocalBlind()) {
            ci.cancel();
        }
    }

    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void nosensesmod$hideBlindClouds(
            FrameGraphBuilder frameGraphBuilder,
            Matrix4f positionMatrix,
            Matrix4f projectionMatrix,
            CloudRenderMode renderMode,
            Vec3d cameraPos,
            float ticks,
            int color,
            float cloudHeight,
            CallbackInfo ci
    ) {
        if (ClientBlindState.isLocalBlind()) {
            ci.cancel();
        }
    }
}
