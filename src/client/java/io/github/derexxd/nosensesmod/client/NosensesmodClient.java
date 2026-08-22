package io.github.derexxd.nosensesmod.client;

import io.github.derexxd.nosensesmod.client.model.BlindfoldModel;
import io.github.derexxd.nosensesmod.client.render.BlindfoldFeatureRenderer;
import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import io.github.derexxd.nosensesmod.network.BlindSyncPayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

public class NosensesmodClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(BlindfoldModel.LAYER, BlindfoldModel::getTexturedModelData);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof PlayerEntityRenderer playerRenderer) {
                registrationHelper.register(new BlindfoldFeatureRenderer(
                        playerRenderer,
                        new BlindfoldModel(context.getPart(BlindfoldModel.LAYER))
                ));
            }
        });
        ClientPlayNetworking.registerGlobalReceiver(BlindSyncPayload.ID, (payload, context) -> context.client().execute(() ->
                ClientBlindState.set(payload.playerId(), payload.blinded())
        ));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> ClientBlindState.clear());
    }
}
