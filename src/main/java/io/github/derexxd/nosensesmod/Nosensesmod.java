package io.github.derexxd.nosensesmod;

import io.github.derexxd.nosensesmod.command.MuteCommand;
import io.github.derexxd.nosensesmod.state.MuteState;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nosensesmod implements ModInitializer {
    public static final String MOD_ID = "nosensesmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                MuteCommand.register(dispatcher)
        );
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) ->
                MuteState.clear(handler.player.getUuid())
        );
        LOGGER.info("NoSensesMod loaded");
    }
}
