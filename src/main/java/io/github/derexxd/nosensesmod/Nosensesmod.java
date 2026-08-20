package io.github.derexxd.nosensesmod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Nosensesmod implements ModInitializer {
    public static final String MOD_ID = "nosensesmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("NoSensesMod loaded");
    }
}
