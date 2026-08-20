package io.github.derexxd.nosensesmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import io.github.derexxd.nosensesmod.state.DeafState;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public final class DeafCommand {
    private DeafCommand() {
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("deaf")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(context -> {
                                    ServerPlayerEntity target = EntityArgumentType.getPlayer(context, "player");
                                    boolean deafened = DeafState.toggle(target.getUuid());
                                    String key = deafened
                                            ? "command.nosensesmod.deaf.enabled"
                                            : "command.nosensesmod.deaf.disabled";
                                    context.getSource().sendFeedback(
                                            () -> Text.translatable(key, target.getDisplayName()),
                                            true
                                    );
                                    return Command.SINGLE_SUCCESS;
                                }))
        );
    }
}
