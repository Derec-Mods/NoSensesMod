package io.github.derexxd.nosensesmod;

import io.github.derexxd.nosensesmod.command.BlindCommand;
import io.github.derexxd.nosensesmod.command.DeafCommand;
import io.github.derexxd.nosensesmod.command.MuteCommand;
import io.github.derexxd.nosensesmod.effect.BlindEffects;
import io.github.derexxd.nosensesmod.state.BlindState;
import io.github.derexxd.nosensesmod.state.DeafState;
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
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            MuteCommand.register(dispatcher);
            BlindCommand.register(dispatcher);
            DeafCommand.register(dispatcher);
        });
        BlindEffects.register();
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            MuteState.clear(handler.player.getUuid());
            BlindState.clear(handler.player.getUuid());
            DeafState.clear(handler.player.getUuid());
        });
        LOGGER.info("NoSensesMod loaded");
    }
}
