package io.github.derexxd.nosensesmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import io.github.derexxd.nosensesmod.state.MuteState;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public final class MuteCommand {
    private MuteCommand() {
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("mute")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(CommandManager.argument("player", EntityArgumentType.player())
                                .executes(context -> {
                                    ServerPlayerEntity target = EntityArgumentType.getPlayer(context, "player");
                                    boolean muted = MuteState.toggle(target.getUuid());
                                    String key = muted
                                            ? "command.nosensesmod.mute.enabled"
                                            : "command.nosensesmod.mute.disabled";
                                    context.getSource().sendFeedback(
                                            () -> Text.translatable(key, target.getDisplayName()),
                                            true
                                    );
                                    return Command.SINGLE_SUCCESS;
                                }))
        );
    }
}
