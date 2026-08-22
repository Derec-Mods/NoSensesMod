package io.github.derexxd.nosensesmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import io.github.derexxd.nosensesmod.state.DeafState;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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

                                    SoundEvent sound = deafened
                                            ? SoundEvents.BLOCK_CONDUIT_DEACTIVATE
                                            : SoundEvents.BLOCK_CONDUIT_ACTIVATE;
                                    float pitch = deafened ? 1.0f : 1.2f;
                                    target.playSoundToPlayer(sound, SoundCategory.PLAYERS, 1.0f, pitch);

                                    ServerPlayerEntity sourcePlayer = context.getSource().getPlayer();
                                    if (sourcePlayer != null && sourcePlayer != target) {
                                        sourcePlayer.playSoundToPlayer(sound, SoundCategory.PLAYERS, 0.8f, pitch);
                                    }

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
