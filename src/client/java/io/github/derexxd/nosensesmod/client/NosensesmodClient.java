package io.github.derexxd.nosensesmod.client;

import io.github.derexxd.nosensesmod.client.model.BlindfoldModel;
import io.github.derexxd.nosensesmod.client.model.GagModel;
import io.github.derexxd.nosensesmod.client.model.HeadphoneModel;
import io.github.derexxd.nosensesmod.client.render.BlindfoldFeatureRenderer;
import io.github.derexxd.nosensesmod.client.render.GagFeatureRenderer;
import io.github.derexxd.nosensesmod.client.render.HeadphoneFeatureRenderer;
import io.github.derexxd.nosensesmod.client.state.ClientBlindState;
import io.github.derexxd.nosensesmod.client.state.ClientDeafState;
import io.github.derexxd.nosensesmod.client.state.ClientMuteState;
import io.github.derexxd.nosensesmod.network.BlindSyncPayload;
import io.github.derexxd.nosensesmod.network.DeafSyncPayload;
import io.github.derexxd.nosensesmod.network.MuteSyncPayload;
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
        EntityModelLayerRegistry.registerModelLayer(GagModel.LAYER, GagModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HeadphoneModel.LAYER, HeadphoneModel::getTexturedModelData);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityRenderer instanceof PlayerEntityRenderer playerRenderer) {
                registrationHelper.register(new BlindfoldFeatureRenderer(
                        playerRenderer,
                        new BlindfoldModel(context.getPart(BlindfoldModel.LAYER))
                ));
                registrationHelper.register(new GagFeatureRenderer(
                        playerRenderer,
                        new GagModel(context.getPart(GagModel.LAYER))
                ));
                registrationHelper.register(new HeadphoneFeatureRenderer(
                        playerRenderer,
                        new HeadphoneModel(context.getPart(HeadphoneModel.LAYER))
                ));
            }
        });
        ClientPlayNetworking.registerGlobalReceiver(BlindSyncPayload.ID, (payload, context) -> context.client().execute(() ->
                ClientBlindState.set(payload.playerId(), payload.blinded())
        ));
        ClientPlayNetworking.registerGlobalReceiver(MuteSyncPayload.ID, (payload, context) -> context.client().execute(() ->
                ClientMuteState.set(payload.playerId(), payload.muted())
        ));
        ClientPlayNetworking.registerGlobalReceiver(DeafSyncPayload.ID, (payload, context) -> context.client().execute(() ->
                ClientDeafState.set(payload.playerId(), payload.deafened())
        ));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            ClientBlindState.clear();
            ClientMuteState.clear();
            ClientDeafState.clear();
        });
    }
}
