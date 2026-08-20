package io.github.derexxd.nosensesmod.voice;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.MicrophonePacketEvent;
import io.github.derexxd.nosensesmod.Nosensesmod;
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
    }

    private void onMicrophonePacket(MicrophonePacketEvent event) {
        VoicechatConnection sender = event.getSenderConnection();
        if (sender == null) {
            return;
        }
        if (MuteState.isMuted(sender.getPlayer().getUuid())) {
            event.cancel();
        }
    }
}
