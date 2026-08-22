package io.github.derexxd.nosensesmod.network;

import io.github.derexxd.nosensesmod.Nosensesmod;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record BlindSyncPayload(UUID playerId, boolean blinded) implements CustomPayload {
    public static final Id<BlindSyncPayload> ID = new Id<>(Identifier.of(Nosensesmod.MOD_ID, "blind_sync"));
    public static final PacketCodec<RegistryByteBuf, BlindSyncPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC,
            BlindSyncPayload::playerId,
            PacketCodecs.BOOLEAN,
            BlindSyncPayload::blinded,
            BlindSyncPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
