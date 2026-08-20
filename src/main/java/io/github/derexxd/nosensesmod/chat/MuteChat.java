package io.github.derexxd.nosensesmod.chat;

import io.github.derexxd.nosensesmod.state.MuteState;
import io.github.derexxd.nosensesmod.util.ChatModifier;
import net.fabricmc.fabric.api.message.v1.ServerMessageDecoratorEvent;

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
}
