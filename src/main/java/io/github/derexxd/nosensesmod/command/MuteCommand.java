package io.github.derexxd.nosensesmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import io.github.derexxd.nosensesmod.network.CosmeticSync;
import io.github.derexxd.nosensesmod.state.MuteState;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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
                                    if (target.getServer() != null) {
                                        CosmeticSync.broadcastMute(target.getServer(), target.getUuid(), muted);
                                    }

                                    SoundEvent sound = muted
                                            ? SoundEvents.ITEM_BUNDLE_INSERT
                                            : SoundEvents.ITEM_BUNDLE_REMOVE_ONE;
                                    float pitch = muted ? 0.7f : 1.3f;
                                    target.playSoundToPlayer(sound, SoundCategory.PLAYERS, 1.0f, pitch);

                                    ServerPlayerEntity sourcePlayer = context.getSource().getPlayer();
                                    if (sourcePlayer != null && sourcePlayer != target) {
                                        sourcePlayer.playSoundToPlayer(sound, SoundCategory.PLAYERS, 0.8f, pitch);
                                    }

                                    String prefix = muted ? "Muted " : "Unmuted ";
                                    context.getSource().sendFeedback(
                                            () -> Text.literal(prefix).append(target.getDisplayName()).append("!!"),
                                            true
                                    );
                                    return Command.SINGLE_SUCCESS;
                                }))
        );
    }
}
