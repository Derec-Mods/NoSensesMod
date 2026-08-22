package io.github.derexxd.nosensesmod.network;

import io.github.derexxd.nosensesmod.Nosensesmod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record MuteSyncPayload(UUID playerId, boolean muted) implements CustomPayload {
    public static final Id<MuteSyncPayload> ID = new Id<>(Identifier.of(Nosensesmod.MOD_ID, "mute_sync"));
    public static final PacketCodec<RegistryByteBuf, MuteSyncPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC,
            MuteSyncPayload::playerId,
            PacketCodecs.BOOLEAN,
            MuteSyncPayload::muted,
            MuteSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
