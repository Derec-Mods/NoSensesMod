package io.github.derexxd.nosensesmod.chat;

import io.github.derexxd.nosensesmod.state.MuteState;
import io.github.derexxd.nosensesmod.util.ChatModifier;
import net.fabricmc.fabric.api.message.v1.ServerMessageDecoratorEvent;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public final class MuteChat {
    private MuteChat() {
    }

    public static void register() {
        ServerMessageDecoratorEvent.EVENT.register(ServerMessageDecoratorEvent.CONTENT_PHASE, (sender, message) -> {
            if (sender == null || !MuteState.isMuted(sender.getUuid())) {
                return message;
            }
            return ChatModifier.muffle(message);
        });
    }

    public static SignedMessage muffleIfMuted(ServerCommandSource source, SignedMessage message) {
        ServerPlayerEntity sender = source.getPlayer();
        if (sender == null || !MuteState.isMuted(sender.getUuid())) {
            return message;
        }
        return message.withUnsignedContent(ChatModifier.muffle(message.getContent()));
    }
}
