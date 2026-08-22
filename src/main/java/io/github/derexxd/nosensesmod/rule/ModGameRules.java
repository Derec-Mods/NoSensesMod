package io.github.derexxd.nosensesmod.rule;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public final class ModGameRules {
    public static final GameRules.Key<GameRules.BooleanRule> SHARED_DEATH =
            GameRuleRegistry.register("sharedDeath", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

    private ModGameRules() {
    }

    public static void register() {
    }
}
