package io.github.derexxd.nosensesmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import io.github.derexxd.nosensesmod.effect.BlindEffects;
import io.github.derexxd.nosensesmod.state.BlindState;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public final class BlindCommand {
    private BlindCommand() {
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("blind")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(context -> {
                                    ServerPlayerEntity target = EntityArgumentType.getPlayer(context, "player");
                                    boolean blinded = BlindState.toggle(target.getUuid());
                                    if (blinded) {
                                        BlindEffects.apply(target);
                                    } else {
                                        BlindEffects.remove(target);
                                    }
                                    String key = blinded
                                            ? "command.nosensesmod.blind.enabled"
                                            : "command.nosensesmod.blind.disabled";
                                    context.getSource().sendFeedback(
                                            () -> Text.translatable(key, target.getDisplayName()),
                                            true
                                    );
                                    return Command.SINGLE_SUCCESS;
                                }))
        );
    }
}
