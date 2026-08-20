package io.github.derexxd.nosensesmod.voice;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EntitySoundPacketEvent;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.LocationalSoundPacketEvent;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import de.maxhenkel.voicechat.api.events.PacketEvent;
import de.maxhenkel.voicechat.api.events.StaticSoundPacketEvent;
import io.github.derexxd.nosensesmod.Nosensesmod;
import io.github.derexxd.nosensesmod.state.DeafState;
import io.github.derexxd.nosensesmod.state.MuteState;

public class NoSensesVoicePlugin implements VoicechatPlugin {
    @Override
    public String getPluginId() {
        return Nosensesmod.MOD_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        Nosensesmod.LOGGER.info("Simple Voice Chat plugin loaded");
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(MicrophonePacketEvent.class, this::onMicrophonePacket);
        registration.registerEvent(EntitySoundPacketEvent.class, this::onSoundPacket);
        registration.registerEvent(LocationalSoundPacketEvent.class, this::onSoundPacket);
        registration.registerEvent(StaticSoundPacketEvent.class, this::onSoundPacket);
    }

    private void onMicrophonePacket(MicrophonePacketEvent event) {
        VoicechatConnection sender = event.getSenderConnection();
        if (sender == null || sender.getPlayer() == null) {
            return;
        }
        if (MuteState.isMuted(sender.getPlayer().getUuid())) {
            event.cancel();
        }
    }

    private void onSoundPacket(PacketEvent<?> event) {
        VoicechatConnection receiver = event.getReceiverConnection();
        if (receiver == null || receiver.getPlayer() == null) {
            return;
        }
        if (DeafState.isDeaf(receiver.getPlayer().getUuid())) {
            event.cancel();
        }
    }
}
