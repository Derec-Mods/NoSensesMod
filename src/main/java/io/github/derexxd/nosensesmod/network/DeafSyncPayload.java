package io.github.derexxd.nosensesmod.network;

import io.github.derexxd.nosensesmod.Nosensesmod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record DeafSyncPayload(UUID playerId, boolean deafened) implements CustomPayload {
    public static final Id<DeafSyncPayload> ID = new Id<>(Identifier.of(Nosensesmod.MOD_ID, "deaf_sync"));
    public static final PacketCodec<RegistryByteBuf, DeafSyncPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC,
            DeafSyncPayload::playerId,
            PacketCodecs.BOOLEAN,
            DeafSyncPayload::deafened,
            DeafSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
