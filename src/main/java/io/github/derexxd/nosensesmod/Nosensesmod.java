package io.github.derexxd.nosensesmod;

import io.github.derexxd.nosensesmod.chat.MuteChat;
import io.github.derexxd.nosensesmod.command.BlindCommand;
import io.github.derexxd.nosensesmod.command.DeafCommand;
import io.github.derexxd.nosensesmod.command.MuteCommand;
import io.github.derexxd.nosensesmod.effect.BlindEffects;
import io.github.derexxd.nosensesmod.event.SharedDeathHandler;
import io.github.derexxd.nosensesmod.network.BlindSyncPayload;
import io.github.derexxd.nosensesmod.network.MuteSyncPayload;
import io.github.derexxd.nosensesmod.network.CosmeticSync;
import io.github.derexxd.nosensesmod.rule.ModGameRules;
import io.github.derexxd.nosensesmod.state.BlindState;
import io.github.derexxd.nosensesmod.state.DeafState;
import io.github.derexxd.nosensesmod.state.MuteState;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nosensesmod implements ModInitializer {
    public static final String MOD_ID = "nosensesmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModGameRules.register();
        SharedDeathHandler.register();
        PayloadTypeRegistry.playS2C().register(BlindSyncPayload.ID, BlindSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(MuteSyncPayload.ID, MuteSyncPayload.CODEC);
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            MuteCommand.register(dispatcher);
            BlindCommand.register(dispatcher);
            DeafCommand.register(dispatcher);
        });
        BlindEffects.register();
        MuteChat.register();
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            CosmeticSync.sendAllBlind(handler.player);
            CosmeticSync.sendAllMute(handler.player);
        });
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            boolean wasMuted = MuteState.isMuted(handler.player.getUuid());
            MuteState.clear(handler.player.getUuid());
            boolean wasBlind = BlindState.isBlind(handler.player.getUuid());
            BlindState.clear(handler.player.getUuid());
            DeafState.clear(handler.player.getUuid());
            if (wasMuted) {
                CosmeticSync.broadcastMute(server, handler.player.getUuid(), false);
            }
            if (wasBlind) {
                CosmeticSync.broadcastBlind(server, handler.player.getUuid(), false);
            }
        });
        LOGGER.info("NoSensesMod loaded");
    }
}
