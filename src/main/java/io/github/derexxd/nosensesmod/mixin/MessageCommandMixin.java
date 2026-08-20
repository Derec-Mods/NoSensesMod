package io.github.derexxd.nosensesmod.mixin;

import io.github.derexxd.nosensesmod.chat.MuteChat;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.command.MessageCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Collection;

@Mixin(MessageCommand.class)
public abstract class MessageCommandMixin {
    @ModifyVariable(method = "execute", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private static SignedMessage nosensesmod$muffleMutedWhisper(
            SignedMessage message,
            ServerCommandSource source,
            Collection<ServerPlayerEntity> targets
    ) {
        return MuteChat.muffleIfMuted(source, message);
    }
}
